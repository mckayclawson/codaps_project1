import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class Group1 is the class that processes a
 * file of text. If it finds a word then it
 * it asks the monitor object to add the word 
 * to the found words list
 * 
 * @author McKay Clawson (msc3254)
 *
 */

public class Group1 implements Runnable {
	private String fileName;
	private FoundWords fw;
	private String[] targetWords;
	BufferedReader br;
	
	/**
	 * Constructor for Group1
	 * @param fw - the FoundWords Monitor object that holds the found target words
	 * @param fileName - the file this thread is processing
	 * @param targetWords - A list of target words to look for
	 */
	public Group1(FoundWords fw,String fileName, String[] targetWords){
		this.fileName = fileName;
		this.fw = fw;
		this.targetWords = targetWords;
	}
	
	/**
	 * Runs the thread and parses the entire file specified
	 * if it finds a word in the targetWords list it will
	 * ask the monitor object to add it.
	 */
	public void run() {
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			
			while((line = br.readLine()) != null){
				String[] lineWords = line.split("[^a-zA-Z']");
				for(String word : lineWords){
					for(String tword : targetWords){
						if(word.toLowerCase().equals(tword)){
							fw.put(word.toLowerCase(), fileName);
						}
					}
				}
			}
			fw.processed(fileName);
			br.close();
		} catch (FileNotFoundException e) {
			//Shouldn't Happen as I already checked that this file exists
		} catch (IOException e) {
			//Shouldn't happen
		}
	}
	
	
}
