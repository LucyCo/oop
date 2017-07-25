package clids.ex4.main;
import java.util.ArrayList;
import clids.ex4.method.AllMethodCalls;
import clids.ex4.method.MethodCall;
import clids.ex4.method.Methods;
import clids.ex4.method.isMethodCallLegal;
import clids.ex4.toolbox.Divider;
import clids.ex4.variable.CreateVariables;
import clids.ex4.variable.Variable;
import clids.ex4.variable.VerifyVariable;
import java.util.LinkedList;
import clids.ex4.exception.CompilationException;
/**
 * This class gets from class Divider the divided file and manage the whole 
 * operation and interaction of the classes.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class Manager {
	private final static String exception = "Syntax error";
	/**
	 * this method verifies syntax and legality for the given file,
	 * throws a compilation exception if the file is not legal.
	 * @param array the given file as a string array
	 * @throws compilation exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static void manage(ArrayList<String> array) throws CompilationException {
		//a simple syntax error
		if(!SyntaxError.isSyntaxLegal(array)) {
			throw new CompilationException(exception);
		}
		//divides the file to methods, method calls and variables
		Divider divider = null;
		try{
			Divider.divider(array);
		} catch (CompilationException e1) {
			throw(e1);
		}
		//create the type variable from array of strings
		LinkedList<MethodCall> methodCalls1 = AllMethodCalls.methodCalls(Divider.methodCalls);
		LinkedList<Variable> members1 = null;
		try {
			members1 = CreateVariables.createVarFromArray(Divider.variables);
		} catch (CompilationException e1) {
			throw(e1);
		}
		//check whether the methods are legal
		ArrayList<LinkedList> variables = new ArrayList<LinkedList>();
		variables.add(members1);
		Methods methodsCheck = null;
		try{
			for(int i=0; i<Divider.methods.size(); i++) {
				methodsCheck = new Methods(Divider.methods.get(i), variables);
			}
		}catch(CompilationException e1) {
			throw(e1);
		}
		//check whether the method calls are legal
		try{
			isMethodCallLegal.isLegal(Divider.methodDeclarations, methodCalls1);
		}catch(CompilationException e1) {
			throw(e1);
		}
		//verify that the global variables are legal
		try {
			VerifyVariable.verify(variables, true);
		}catch(CompilationException e1){
			throw(e1);
		}
	}
}
