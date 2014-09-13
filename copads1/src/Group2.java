/**
 * Class Group2 provides the printing requirement
 * of the program. It's only purpose is to check
 * and see if the particular word it is looking
 * for has been found in the particular textfile
 * specified.
 * 
 * @author McKay Clawson (msc3254)
 *
 */
public class Group2 implements Runnable {
	private String word;
	private String fileName;
	private FoundWords fw;
	
	/**
	 * Constructor for Group2
	 * @param fw - the FoundWords Monitor object that holds the found target words
	 * @param word - the word this Thread is looking for
	 * @param fileName - the file this thread is looking for 'word' in
	 */
	public Group2(FoundWords fw, String word, String fileName){
		this.word = word;
		this.fileName = fileName;
		this.fw = fw;
	}
	
	/**
	 * Runs the thread by asking the FoundWords object to
	 * check and see if its word has been found in its file
	 */
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(fw.check(word, fileName)){
				System.out.println(word + " " + fileName);
			}
		} catch (InterruptedException e) {
			//Shouldn't Happen
		}
		
	}
}
