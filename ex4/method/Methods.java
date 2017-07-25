package clids.ex4.method;
import java.util.*;
import java.util.regex.*;

import clids.ex4.exception.CompilationException;
import clids.ex4.toolbox.RegExp;
import clids.ex4.variable.CreateVariables;
import clids.ex4.variable.Variable;
import clids.ex4.variable.VerifyVariable;


public class Methods{
	//an array list of String that represents the line of the method we want to check
	ArrayList<String> methodLines;
	@SuppressWarnings("rawtypes")
	/*an arrayList of linked list that represent the variable of the class
	on the first cell there are global variable, on the second local variables (that 
	were declared within the method) and on the third and above all variables
	that were declared on the method's blocks */ 
	ArrayList<LinkedList> vars;
	//the method declaration that are in the method
	LinkedList<Variable> varOfMethodDec;
	//An array of String that represents the variables on the declaration of the method.
	protected static String[] decVariables; 
	private final static String exception = "Illegal method declaration";
	private final static String exception2 = "No return statement";
	private final static String exception3 = "Closer paranthesis missing";
	private final static String exception4 = "No opener in if/while block";
	private final static String exception5 = "Illegal if/while statement";
	private final static String exception6 = "Illegal variable";
	private final static String boolintdoub = "(boolean|int|double)";
	/**
	 * returns true if the given method is legal
	 * 
	 * @param method string array list of one method from the original code
	 * @param vars the variables of the outer block
	 * @throws CompilationException if the method is found illegal
	 */
	@SuppressWarnings("rawtypes")
	public Methods(ArrayList<String> method, ArrayList<LinkedList> vars) throws CompilationException{
		this.methodLines = method;
		this.vars = vars;
		//check that the method declaration is legal
		if(!isDeclarationLegal(method.get(0))) throw new CompilationException(exception);
		//check that the last two lines are legal
		// the line before the last should contain the word return and the last line should contain '}'
		String lastLine = method.get(method.size()-2);
		lastLine = lastLine.trim();
		if(!lastLine.equals(RegExp.RETURN)) throw new CompilationException(exception2);
		lastLine = method.get(method.size()-1);
		if(!lastLine.equals(RegExp.CLOSER1)) throw new CompilationException(exception3);
		//send it to method that check if the content of the method is legal
		try {
			isMethodLegal(method);
		}catch(CompilationException e){
			throw(e);
		}
	}
	/*
	 * This method gets an array list of string represents the method's line 
	 * reads them line by line and check if the lines are legal. 
	 */
	@SuppressWarnings("unchecked")
	private boolean isMethodLegal(ArrayList<String>method) throws CompilationException{
		LinkedList<Variable> linkey = new LinkedList<Variable>();
		vars.add(linkey);
		String current = RegExp.emptyStr;
		boolean isVarOk ;
		int opener = 0;
		//check each line of the method's content except the last two lines that we already check
		// in the constructor.
		for(int i = 1; i<method.size();i++ ){
			current = method.get(i);
			current = current.trim();
			//in case the current line starts with if or while
			if (current.startsWith(RegExp.IF)||(current.startsWith(RegExp.WHILE))){
				//check the current line ends with "{"
				if(!current.endsWith(RegExp.OPENER1)) throw new CompilationException(exception4);
				else {
					if(!conditionStatement(current)) throw new CompilationException(exception5);
					++opener;
					continue;
				}
			}
			if(current.endsWith(RegExp.CLOSER1)) {
				//if we reach the end of the block we
				isVarOk = VerifyVariable.verify(vars, false);
				//if the variables are legal we can delete them from the variable arrayList
				if (isVarOk) {
					vars.remove(vars.size()-1);
				}
				else {
					throw new CompilationException(exception6);
				}
				--opener;
				continue;
			}
			//if the line is not a condition statement and not a method calling
			//it is a variable and we need to check this variable is legal
			if(!current.matches(RegExp.METHOD_CALL_REGEX)&&!current.matches(RegExp.RETURN)){
				Variable var = CreateVariables.createVarFromString(current);
				if(varOfMethodDec!=null){
					for(int k=0; k<varOfMethodDec.size(); k++) {
						vars.get(vars.size()-1).add(varOfMethodDec.get(k));
					}
					varOfMethodDec=null;
				}
				vars.get(vars.size()-1).add(var);
				continue;
			}
		}
		//in case the number of opener brackets and closer is not equal
		// we return false.
		if(opener!=0) return false;
		return true;
	}
	/*
	 * This method gets a String that suspected to be a "if" or "while" condition
	 * statement and return true if the line is a legal while/if command in java.
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private boolean conditionStatement(String line) throws CompilationException{
		//in case the line does not end with "{" it would return false
		if(!line.endsWith(RegExp.OPENER1)) return false;
		ArrayList<String> linkey = new ArrayList<String>();
		// now we check only the content inside the parenthesis
		int firstSpace= line.indexOf(RegExp.OPENER);
		String insidePar1 = line.substring(firstSpace,line.length()-1);
		insidePar1 = insidePar1.trim();
		insidePar1 = insidePar1.substring(1, insidePar1.length()-1);
		String[] conditions = insidePar1.split(RegExp.insidePar);
		for(String condition : conditions){
			condition = condition.trim();
			if (condition.isEmpty()){
				return false;
			}
			if(!condition.matches(RegExp.WHILE_IF_DECLARATION)){
				linkey.add(condition);
			}
		}
		if(linkey!=null){
			LinkedList<Variable> link = CreateVariables.createVarFromArray(linkey);
			vars.add(link);
			for(int i=0; i<linkey.size(); i++){
				boolean ini = false;
				for(int k=vars.size()-1; k>=0; k--){
					ini = VerifyVariable.isInitialized(link.get(i), vars.get(k));
					if(ini){
						if(!link.get(i).type.matches(boolintdoub)||link.get(i).value==null) return false;
					}
				}
			}
			return VerifyVariable.verify(vars, false);
		}
		return true;
	}
	/*
	 * This method checks if the text String matches a method legal declaration 
	 */
	private boolean isMatchMethDec(String text){
		Pattern pattern = Pattern.compile(RegExp.INNER);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			return true;
		}
		return false;
	}
	/*
	 * This method gets a line that suspected to be a method declareation
	 * and return true if the line is a legal method declaration.
	 */
	private boolean isDeclarationLegal (String methodDec) throws CompilationException{
		int start = methodDec.indexOf(RegExp.OPENER);
		int end = methodDec.indexOf(RegExp.CLOSER);
		String content = methodDec.substring(start+1, end);
		decVariables = content.split(RegExp.COMMA_REGEX);
		//in case there are no parameters on the method's declaration
		if(decVariables[0].equals(RegExp.emptyStr)) return true;
		for(int i =0; i<decVariables.length; i++){
			decVariables[i] = decVariables[i].trim();
			if (!isMatchMethDec(decVariables[i])){
				return false;
			}
			if(decVariables.length>1){
				for(int k=0; k<decVariables.length-1; k++){
					if(decVariables[k].split(RegExp.emptyStrSplit)[1].matches
							(decVariables[k+1].split(RegExp.emptyStrSplit)[1])){
						return false;//two params with the same name
					}
				}
			}
		}
		varOfMethodDec = new LinkedList<Variable>();
		for(int i=0; i<decVariables.length;i++){
			Variable variable = new Variable();
			 variable = CreateVariables.createVarFromString(decVariables[i]);
			 variable.isMethodDecParam = true;
			 varOfMethodDec.add(variable);
		}
		return true;	
	}
}