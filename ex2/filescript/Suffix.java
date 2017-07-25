package clids.ex2.filescript;
import java.io.*;
/**
 * This class determines if a given file belongs to a "suffix" filter
 * which means check if a given string equals to the given file's name's suffix
 * @author alonaoz and lauren
 *
 */
public class Suffix extends Filter {
	
	/**
	 * this method gets a file and a string and return true if the String matches the 
	 * file suffix.
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		if(param1==null){
			throw new Exception();
		}
		String fileName = file.getName();
		int size = param1.length();
		String suff = fileName.substring((fileName.length()-size), fileName.length());
		if (suff.equals(param1)){
			isMatch = true;
		}
		else {
			isMatch = false;
		}
		if(param2!=null){
			if(param2.equals("NOT")) {
				return !isMatch;
			}
		}
		return isMatch;
	}
}