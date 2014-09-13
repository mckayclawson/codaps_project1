import java.util.ArrayList;

/**
 * Class FoundWords is the Monitor Object
 * with Conditions for Project1. It handles
 * all found target words and the processed files.
 * 
 * @author McKay Clawson (msc3254)
 *
 */

public class FoundWords {
	
	private ArrayList<ArrayList<String>> wordList = new ArrayList<ArrayList<String>>();
	private ArrayList<String> processedList = new ArrayList<String>();
	
	/**
	 * Once a word has been found this method checks to see if that word
	 * has already been found and if it has not it adds the word to 
	 * the list of found words. It then notifies all Threads since
	 * a new word has been found and it may be the word that thread is 
	 * looking for.
	 * @param word - the traget word to add to the found list
	 * @param fileName - the file that the word was found in
	 */
	public synchronized void put(String word, String fileName){
		for(ArrayList<String> al : wordList){
			if(al.get(0).equals(word) && al.get(1).equals(fileName)){
				return;
			}
		}
		ArrayList<String> temp = new ArrayList<String>(2);
		temp.add(word);
		temp.add(fileName);
		wordList.add(temp);
		notifyAll();
	}
	
	/**
	 * Marks a file as processed and then calls notifyAll
	 * so that all threads that are associated with this file
	 * knows that their word was not found
	 * @param fileName -  the file to add to the processedList
	 */
	public synchronized void processed(String fileName){
		processedList.add(fileName);
		notifyAll();
	}
	
	/**
	 * checks to see a word has been found and wait if it has not
	 * Will return false once the file is processed and the word 
	 * has not been found
	 * @param word - word to check for
	 * @param fileName - file to check in
	 * @return true if the word was found false otherwise
	 * @throws InterruptedException - The usual interrupted exception associated with wait()
	 */
	public synchronized boolean check(String word, String fileName) throws InterruptedException{
		boolean found = false;
		while(!found){
			for(ArrayList<String> al : wordList){
				if(al.get(0).equals(word) && al.get(1).equals(fileName)){
					found = true;
					return found;
				}
			}
			if(isProcessed(fileName)){
				return false;
			}
			if(!found){
				wait();
			}
		}
		return false;
	}
	
	/**
	 * A small reusable method to check if a file has been processed.
	 * @param fileName - file to check if processed
	 * @return true if the file has been processed false otherwise
	 */
	public boolean isProcessed(String fileName){
		for(String file : processedList){
			if(fileName.equals(file)){
				return true;
			}
		}
		return false;
	}
}
