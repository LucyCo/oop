package clids.ex2.filescript;
import java.io.*;
/**
 * This class determines if a given file belongs to a "Hidden" filter
 * @author alonaoz and lauren
 *
 */
public class Hidden extends Filter {
	/**
	 * This method gets a file and a Strign represents "yes" or "not" values and 
	 * check if the file is hidden or not and return the boolean value
	 * matches the string
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		if (param1==null){
			throw new Exception();
		}
		boolean answer;
		if(param1.equals("NO"))
			answer = false;
		else if (param2.equals("YES"))
			answer = true;
		else {
			throw new Exception();
		}
		if (file.isHidden()==answer){
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