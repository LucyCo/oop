package clids.ex2.filescript;
import java.io.*;
/**
 * This class determines if a given file belongs to a "prefix" filter
 * which means check if a given string equals to the given file's name's prefix
 * @author alonaoz and lauren
 *
 */
public class Prefix extends Filter {
	/**
	 * This method gets a file and a string and return true if the string 
	 * is equal the the file's name prefix
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		if(param1==null){
			throw new Exception();
		}
		if (file.getName().startsWith(param1)){
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