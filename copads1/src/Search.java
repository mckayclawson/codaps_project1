import java.io.File;

/**
 * Class Search is the main program for Project1. 
 * It will read input from the command line, check
 * to make sure the input is valid based on requirements,
 * start all Group2 Threads and then Start all Group1 
 * Threads.
 * 
 * 
 * @author McKay Clawson (msc3254)
 * 
 */
public class Search {
	/**
	 * Main Method for Project1
	 * 
	 * @param args - input from the command line
	 */
	public static void main(String[] args){
		if(!checkArgs(args)){
			System.err.print("Usage: Search <files> <words> \nwords must be letters only, files must exist");
			return;
		}
		String fileString = args[0];
		String wordString = args[1];
		String[] wordList = wordString.split(",");
		String[] fileList = fileString.split(",");
		FoundWords fw =  new FoundWords();
		
		for(int i = 0; i < wordList.length; i++){
			wordList[i] = wordList[i].toLowerCase();
		}
		
		for(String file : fileList){
			for(String word : wordList){
				new Thread (new Group2(fw, word, file)).start();
			}
		}
		
		for(String file : fileList){
			new Thread (new Group1(fw,file,wordList)).start();
		}

	}
	
	/**
	 * A method that checks the args list to make sure they adhere to the standards stated in the requirements document 
	 * @param args - the args supplied from the command line
	 * @return true if the args are valid false otherwise
	 */
	public static boolean checkArgs(String[] args){
		if(args.length  != 2){
			return false;
		}
		String fileString = args[0];
		String wordString = args[1];
		String [] wordList = wordString.split(",");
		String[] fileList = fileString.split(",");
		for(String word : wordList){
			char[] wordChars = word.toCharArray();
			for(char c : wordChars){
				if(!Character.isLetter(c)){
					return false;
				}
			}
		}
		for(String file : fileList){
			if(!(new File(file).isFile())){
				return false;
			}
		}
		return true;
	}
}
