package clids.ex2.filescript;
import java.io.*;
/**
 * This class determines if a given file belongs to a "Executable" filter
 * 
 * @author alonaoz and lauren 
 *
 */
public class Executable1 extends Filter {
	/**
	 * This method gets a file and a String represents the words "yes"
	 * of "not" and check if the file is executable, and return the boolean 
	 * value that matches the String value
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		boolean answer;
		if (param1==null){
			throw new Exception();
		}
		if(param1.equals("NO"))
			answer = false;
		else if (param1.equals("YES"))
			answer = true;
		else {
			throw new Exception();
		}
		if (file.canExecute()==answer){
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