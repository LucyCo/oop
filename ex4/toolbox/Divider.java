package clids.ex4.toolbox;

import java.util.ArrayList;
import java.util.regex.Matcher;

import clids.ex4.exception.CompilationException;
/**
 * This class divide the file into 3 groups: methods, method calls and variables.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class Divider {
	private final static String exception = "No return declaration";
	private static int listCount;
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> methods;
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> methodDeclarations;
	public static ArrayList<String> methodCalls;
	public static ArrayList<String> variables;
	/**
	 * divides the given string array list to methods, method calls and global variables
	 * sublists.
	 * @param array the given string array of the original file
	 * @throws CompilationException if the division hasn't succeeded
	 */
	@SuppressWarnings("rawtypes")
	public static void divider(ArrayList<String> array) throws CompilationException {
		listCount = 0;
		variables = new ArrayList<String>();
		methodCalls = new ArrayList<String>();
		methods = new ArrayList<ArrayList>();
		methodDeclarations = new ArrayList<ArrayList>();
		Matcher matcher;
		for(int i=0; i<array.size(); i++) {
			matcher = RegExp.METHOD_DECLARATION_COMPILED_REGEX.matcher(array.get(i));
			if(matcher.lookingAt()) {
				if(method(array, i)!=null) {
					methodDeclarations.add(methodDeclaration(array.get(i)));
					ArrayList<String> method = method(array, i);
					methods.add(method);
					i = listCount;
					listCount = 0;
					continue;
				}
			}
			if(array.size()<=i) break;
			matcher = RegExp.METHOD_CALL_COMPILED_REGEX.matcher(array.get(i));
			if(!matcher.lookingAt()) {
				variables.add(array.get(i));
			}
		}
		for(int i=0; i<array.size(); i++) {
			String current = array.get(i);
			if(current.endsWith(RegExp.OPENER1)&&!current.contains(RegExp.VOID)&&!current.contains
					(RegExp.WHILE)&&!current.contains(RegExp.IF)){
				throw new CompilationException(exception);
			}
			matcher = RegExp.METHOD_CALL_COMPILED_REGEX.matcher(array.get(i));
			if(matcher.lookingAt()) {
				methodCalls.add(array.get(i));
			}
		}
	}
	/*
	 * Divides the found method fro the rest of the file
	 */
	private static ArrayList<String> method(ArrayList<String> array, int index) {
		ArrayList<String> method = new ArrayList<String>();
		int count = 0;
		for(int i=index; i<array.size(); i++) {
			if(array.get(i).contains(RegExp.OPENER1)) {
				count++;
			}
			if(array.get(i).contains(RegExp.CLOSER1)) {
				count--;
			}
			method.add(array.get(i));
			if(count == 0) {
				listCount = i;
				return method;
			}
		}
		return null;
	}
	/*
	 * splits the method declaration to name and parameters and parameter types
	 */
	private static ArrayList<String> methodDeclaration(String dec) {
		String[] str = dec.split(RegExp.getParamsSplit);
		ArrayList<String> array = new ArrayList<String>();
		for(int i=1; i<str.length; i++) {
			array.add(str[i].trim());
		}
		return array;
	}
}
