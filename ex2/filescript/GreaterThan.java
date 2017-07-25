package clids.ex2.filescript;
import java.io.*;
/**
 * This class determine if a given file belongs to the GreaterThan filter
 * which means if the file's size is greater then the input double variable
 * 
 * @author alonaoz and lauren
 *
 */
public class GreaterThan extends Filter {
	//the conversion number between bytes and k-bytes
	private final int CONVERT = 1024;
	/**
	 * This method gets a file and a string represents a double value
	 * convert the string variable to double and determine if 
	 * the value is greater than the file's size.
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception {
		if(param1==null){
			throw new Exception();
		}
		double input;

		input = Double.parseDouble(param1);
		if (file.length()>=(input*CONVERT)){
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
