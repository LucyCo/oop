package clids.ex2.filescript;
import java.io.*;

/**
 * 
 * @author alonaoz and lauren
 *
 */
public class Writable1 extends Filter {
	/**
	 * This method gets a file and a String represents the words "yes" or "not"
	 * and check if the file is writable or unwritable according to the 
	 * String value.
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		if(param1==null){
			throw new Exception();
		}
		boolean answer;
		if(param1.equals("NO"))
			answer = false;
		else if (param1.equals("YES"))
			answer = true;
		//if the string does not match the two value above we throw an exception
		else {
			throw new Exception();
		}
		if (file.canWrite()==answer){
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