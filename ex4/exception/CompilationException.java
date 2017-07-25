package clids.ex4.exception;
/**
 * This class alert whenever a compilation error occur.
 * 
 * @author laurencohen and alonaoz
 *
 */
@SuppressWarnings("serial")
public class CompilationException extends Exception {
	/**
	 * a compilation exception is thrown when the compiler finds an illegal syntax or
	 * error with the given sjava code file
	 * @param s The string to be printed as the exception is thrown
	 */
	public CompilationException(String s){
		super(s);
	}
}
