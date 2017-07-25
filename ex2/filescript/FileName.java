package clids.ex2.filescript;
import java.io.*;
/**
 * This class determines if a given file belongs to a "file" filter
 * which means check if a given string equals to the given file's name
 * 
 * @author alonaoz and lauren
 *
 */
public class FileName extends Filter {	
	/**
	 * This method gets a file and a String represents the name of the file and 
	 * determine if the given string equals to the file name
	 * @throws Exception 
	 */
	public boolean isMatch(File file, String param1, String param2) throws Exception{
		if(param1==null){
			throw new Exception();
		}
		String fileName = file.getName();
		
		if (fileName.equals(param1)){
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