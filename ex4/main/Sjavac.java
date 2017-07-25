package clids.ex4.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import clids.ex4.exception.CompilationException;
import clids.ex4.toolbox.Parser;

/**
 * This class verifies that the given code file is legal.
 * The exit value for a legal file is 0. If a compilation error is found the compiler
 * terminates with the exit value 1.
 * if the file is not found or illegal arguments are given the compiler will terminate
 * with an exit value of 2.
 * A message will be printed if an error is found.
 * 
 * @author laurencohen alonaoz
 */
public class Sjavac {
	private final static int ZERO = 0;
	private final static int ONE = 1;
	private final static int TWO = 2;
	private final static String exception = 
			"Illegal arguments: IO exception cause Sjava compiler to teminate";
	private final static String exception2 = "File not found";
	
	/**
	 * The main method for the compiler
	 * @param args the path/name for the given file
	 */
	public static void main(String[] args) {
		//check the args is legal
        if (args.length != ONE) {
            System.out.println(exception);
            System.exit(TWO);
        }
		ArrayList<String> str = new ArrayList<String>();
		File file = new File(args[0]);
		//parse the file to a string array list
		try{
			str = Parser.parser(file);
		}catch(FileNotFoundException e){
            System.out.println(exception2);
            System.exit(TWO);			
		} catch (CompilationException e) {
			e.printStackTrace();
			System.exit(ONE);
		}
		//verify that the code is legal
		try{
			Manager.manage(str);
			System.exit(ZERO);
		}catch(CompilationException e1){
			e1.printStackTrace();
			System.exit(ONE);
		}
	}
}
