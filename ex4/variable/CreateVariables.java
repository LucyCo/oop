package clids.ex4.variable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;

import clids.ex4.exception.CompilationException;
import clids.ex4.toolbox.RegExp;
/**
 * This class gets Strings represent variable and turn them to variable objects.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class CreateVariables {
	ArrayList<String> array;
	private final static String exception = "Illegal variable";
	static LinkedList<Variable> myVariables;
	/**
	 * constructor
	 * @param array the given string array
	 */
	public CreateVariables(ArrayList<String> array){
		this.array = array;
	}
	/**
	 * Receives an array of strings representing variables and returns a linked list
	 * of variable objects
	 * @param array
	 */
	public static LinkedList<Variable> createVarFromArray(ArrayList<String> array) 
			throws CompilationException{
		myVariables = new LinkedList<Variable>();
		String[] str;
		for(int i=0; i<array.size(); i++){
			str = array.get(i).split(RegExp.splitVar);
			myVariables.add(createVar(str));
		}
		return myVariables;
	}
	/**
	 * Receives a string representing variable and returns a variable object
	 * @param str string representing a variable
	 */
	public static Variable createVarFromString(String str) throws CompilationException {
		String[] strArray = str.split(RegExp.splitVar);
		try{ 
			return createVar(strArray);
		}catch(CompilationException e){
			throw(e);
		}
	}

	/*
	 * receives a string and returns the given variable.
	 */
	protected static Variable createVar(String[] str) throws CompilationException {
		Variable var = new Variable();
		for(int i=0; i<str.length; i++) {
			if (str[i].equals("final")){
				var.isFinal=true;
				continue;
			}
			Matcher matcher = RegExp.VAR_TYPE_REGEX_COMPILED.matcher(str[i]);
			if (matcher.lookingAt()) {
				var.type=str[i].trim();
				continue;
			}
			else {
				if(str.length-i>2) {
					throw new CompilationException(exception);
				}
				if(str[i].contains(RegExp.COMMA_REGEX)){
					String[] dividedVarName = str[i].split(RegExp.splitVarName);
					for(int k=0; k<dividedVarName.length; k++) {
						Variable var2 = new Variable();
						var2.name=dividedVarName[k].trim();
						var2.isFinal = var.isFinal;
						var2.type = var.type.trim();
						if(str.length-i>1) {
							var.value = str[i+1].trim();
						}
						myVariables.add(var2);
					}
				}
				var.name = str[i].trim();
				if(str.length-i>1) {
					if(str[i+1].contains(RegExp.COMMA_REGEX)){
						String[] dividedVarValue = str[i+1].split(RegExp.splitVarName);
						for(int k=0; k<dividedVarValue.length; k++) {
							Variable var2 = new Variable();
							var2.value = dividedVarValue[k].trim();
							var2.name = var.name;
							var2.isFinal = var.isFinal;
							var2.type = var.type;
							myVariables.add(var2);
						}
					}
					var.value = str[i+1].trim();
					break;
				}
			}
		}
		return var;
	}
}