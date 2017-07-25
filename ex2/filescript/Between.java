package clids.ex2.filescript;
import java.io.*;
/**
 * This class determine if a file matches the Between filter meaning if 
 * file size is between the given numbers.
 * 
 * @author alonaoz and lauren
 *
 */
public class Between extends Filter {
	//the conversion number between bytes and k-bytes
	private final int CONVERT = 1024;
	
	/**
	 * The following method gets a file and a String  represents two double values
	 * and determine whether or not the given file's size is in between the input
	 * numbers. Throws an exception if the first value is greater than the second value.
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception {
		if (param1==null||param2==null) {
			throw new Exception();
		}
		//convert them to double
		double low;
		double high; 
		
		low = Double.parseDouble(param1);     
		high = Double.parseDouble(param2);
		//make sure the low is not greater than the high
		if (low>high) {
			throw new Exception();
		}
		//return true if the file size is in between the input numbers
		if ((file.length()>=(low*CONVERT))&&(file.length()<=(high*CONVERT))){
			isMatch = true;;
		}	
		else{
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