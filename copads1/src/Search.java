import java.io.File;

/**
 * 
 * @author McKay Clawson (msc3254)
 * 
 */

public class Search {
	public static void main(String[] args){
		if(!checkArgs(args)){
			System.err.print("some usage messgae yet to be specificed");
		}
		String fileString = args[0];
		String wordString = args[1];
		String [] wordList = wordString.split(",");
		String[] fileList = fileString.split(",");
		
		
		
	}
	
	/**
	 * A method that checks the args list to make sure they adhere to the standards stated in the requirements document 
	 * @param args
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
