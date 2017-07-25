package clids.ex4.method;
import java.util.ArrayList;
import java.util.LinkedList;

import clids.ex4.exception.CompilationException;
import clids.ex4.variable.VerifyVariable;

/**
 * This class gets an arrayList of method declaration and check if they are legal
 * method calls or not. In case they are not, throws an error.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class isMethodCallLegal {
	private final static String exception = "Illegal method call: wrong parameters";
	private final static String exception2 = "Illegal method call: method doesn't exist";
	private final static String exception3 = "2 methods with the same name";
	/**
	 * determines whether a given method call is legal
	 * @param methodDeclarations all method declarations of the code
	 * @param methodCalls all method calls of the code
	 * @return true if all method calls are legal
	 * @throws CompilationException if an illegal call is found
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isLegal(ArrayList<ArrayList> methodDeclarations, 
			LinkedList<MethodCall> methodCalls) throws CompilationException {
		for(int i=0; i<methodCalls.size(); i++) {
			boolean sameName = false;
			for(int k=0; k<methodDeclarations.size(); k++) {
				MethodCall current = methodCalls.get(i);
				ArrayList<String> methodDec = methodDeclarations.get(k);
				//look for a method with the same name
				if(current.methodName.trim().equals(methodDec.get(0).trim())) {
					//found method with the same name, check for the parameters
					sameName = true;
					if(!checkCallVariables(methodDec, current)) {
						//if found one wrong method call return false;
						throw new CompilationException(exception);
					}
					//found method with the same name and legal parameters, this method call is legal, 
					//continue to the next
					break;
				}
			}
			if(!sameName) throw new CompilationException(exception2);
		}
		//look for a method with the same name (declaration)
		for(int i=0; i<methodDeclarations.size(); i++) {
			for(int k=0; k<methodDeclarations.size(); k++) {
				if(k==i) continue;
				if(methodDeclarations.get(i).get(0).equals
						(methodDeclarations.get(k).get(0))){
					throw new CompilationException(exception3);
				}
			}
		}
		return true;
	}
	/*
	 * return true if the given method call's variables match the method declaration params
	 */
	private static boolean checkCallVariables(ArrayList<String> methodDec, MethodCall current) {
		//check if they have the same number of parameters
		if(!(current.parameters.size()==(methodDec.size()-1)/2)) return false;
		ArrayList<String> array = new ArrayList<String>();
		for(int i=1; i<methodDec.size(); i=i+2) {
			array.add(methodDec.get(i));
		}
		for(int k=1; k<current.parameters.size()||k<array.size(); k++) {
			if(!VerifyVariable.checkVarType(current.parameters.get(k), array.get(k))) {
				if(current.parameters.get(k).equals(methodDec.get(k+2))) return true;
				//if found one variable in the call that doesn't match the type return false
				return false;
			}
		}
		return true;
	}
}
