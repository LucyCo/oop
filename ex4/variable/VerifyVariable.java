package clids.ex4.variable;
import java.util.ArrayList;
import java.util.LinkedList;

import clids.ex4.exception.CompilationException;
import clids.ex4.toolbox.RegExp;

/**
 * This class gets a variables and check if they are legal variables or not.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class VerifyVariable {
	private static final String exception = "Illegal Variable";
	private static final String exception2 = "A final variable's value can't be changed";
	private static final String exception3 = "Illegal variable name";
	private static final String exception4 = "Duplicated variable names";
	private static final String nameOption = "[a-zA-Z][a-zA-Z0-9_]*";
	private static final String nameOption2 = "_+[a-zA-Z0-9_]+";

	/**
	 * This method returns true if the given variable list is legal
	 * @param array ArrayList of lists of variables
	 * @param members true if the variables in the current list are members
	 * @throws Compilation Exception when finds an illegal variable
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean verify(ArrayList<LinkedList> array, boolean members) throws CompilationException {
		if (!verify(array.get(array.size()-1), array, array.size()-1, members)){
			throw new CompilationException(exception);
		}
		return true;
	}
	/*
	 * return true if the given variable list is legal
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean verify(LinkedList<Variable> toVerify, ArrayList<LinkedList> 
	array, int index, boolean members) throws CompilationException {
		for(int i=0; i<toVerify.size(); i++) {
			boolean initialized = false;
			boolean legalValue = false;
			boolean notDuplicated = false;
			for(int k=index; k>=0; k--) {
				//check legality for each of the former
				if(initialized==false) {
					initialized = isInitialized(toVerify.get(i), array.get(k));
				}
				if(legalValue==false) {
					legalValue = isValueLegal(toVerify.get(i), array.get(k));
				}
				if(notDuplicated==false) {
					if (!members) {
						notDuplicated = checkDupAndName(toVerify.get(i), array.get(k));
					}
					else {//check if member name is legal
						notDuplicated = toVerify.get(i).name.matches(nameOption)||
								toVerify.get(i).name.matches(nameOption2);
					}
				}
				//this variable is legal, check the next one.
				if(initialized&&legalValue&&notDuplicated) break;
			}
			if(!(initialized&&legalValue&&notDuplicated)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * returns true if a variable with the same name and a type was found and changes the type
	 * of the given var to the type of the found var with the same name.
	 * @param var the given variable
	 * @param current the current list of variables
	 * @return true if the variable has been initialized
	 * @throws CompilationException if the variable if final and can't be changed
	 */
	public static boolean isInitialized(Variable var, LinkedList<Variable> current) throws CompilationException {
		if (var.type==null) {
			Variable sameName = null;
			//if the variable doesn't have a type, check if is was initialized somewhere else.
			for(int i=0; i<current.size(); i++) {
				//this iteration is on the same variable as the one we are checking, ignore it.
				if(var.equals(current.get(i))) continue;
				if(var.name.equals(current.get(i).name)) {
					//found a variable with the same name.
					sameName = current.get(i);
					if (sameName.type==null) {
						continue;
					}
					else {
						var.type = sameName.type;
					}
					//the initialized variable is final, this var can't be changed
					if(sameName.isFinal&&var.value!=null) {
						throw new CompilationException(exception2);
					}
					var.value = sameName.value;
				}			
			}
			if(sameName==null) {
				return false;
			}
		}
		//this variable is final and no value
		if(var.isFinal&&var.value==null) {
			return false;
		}
		return true;
	}
	/**
	 * returns true if the given value matches the given type
	 * @param value the value of the variable
	 * @param type the type the value needs to be
	 * @return true if the given value matches the given type
	 */
	public static boolean checkVarType(String value, String type) {
		switch(type) {
		case "int":
			if(value.matches(RegExp.INT)) {
				return true;
			}
			break;
		case "double":
			if(value.matches(RegExp.DOUBLE)) {
				return true;
			}
			break;
		case "boolean":
			if(value.matches(RegExp.BOOL)){
				return true;
			}
			break;
		case "char":
			if(value.matches(RegExp.CHAR)) {
				return true;
			}
			break;
		case "String":
			if(value.matches(RegExp.STRING)) {
				return true;
			}
			break;				
		}
		return false;
	}
	/*
	 * returns true if the value of the given variable is legal
	 */
	private static boolean isValueLegal(Variable var, LinkedList<Variable> current) {
		if (var.value!=null) {
			if(var.type != null) {
				if(checkVarType(var.value, var.type)) {
					return true;
				}
				boolean sameScope = false;
				Variable sameNameforVar = null;
				for(int i=0; i<current.size(); i++) {
					if(var.equals(current.get(i))){
						sameScope = true;
					}
				}
				int stopper = current.size();
				for(int i=0; i<stopper; i++) {
					if(sameScope) {
						stopper = i;
					}
					if(var.value.equals(current.get(i).name)&&(!var.equals(current.get(i)))) {
						if (current.get(i).isMethodDecParam||current.get(i).value!=null) {
							//found a variable with the same name.
							sameNameforVar = current.get(i);
							if (var.type.equals(sameNameforVar.type)) {
								return true;
							}
						}
					}			
				}
				return false;
			}
			return false;
		}
		return true;
	}
	/*
	 * returns true if the name of the variable is legal and there isn't another variable
	 * with the same name
	 */
	private static boolean checkDupAndName(Variable var, LinkedList<Variable> current) throws CompilationException {
		for(int i=0; i<current.size(); i++) {
			//illegal name, no letters 
			if(!var.name.matches(nameOption)&&!var.name.matches(nameOption2)){
				throw new CompilationException(exception3);
			}
			if(var.equals(current.get(i))) continue;
			if(var.name.equals(current.get(i).name)&&!current.get(i).isMethodDecParam&&current.get(i).value==null) {
				//found a variable with the same name. regardless of type, this is illegal
				throw new CompilationException(exception4);

			}
		}
		return true;
	}
}
