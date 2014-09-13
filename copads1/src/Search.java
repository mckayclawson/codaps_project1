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
		
	}
	
	/**
	 * 
	 * @param args
	 * @return true if the args are valid based on the standards stated in the requirements document
	 */
	public static boolean checkArgs(String[] args){
		if(args.length  != 2){
			return false;
		}
		String fileString = args[0];
		String wordString = args[1];
		System.out.println(fileString + wordString);
	}
}
