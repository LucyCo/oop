package clids.ex4.main;
import java.util.ArrayList;

import clids.ex4.toolbox.RegExp;
/**
 * This class checks the initial syntax error that may occur when reading the file.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class SyntaxError {
	/**
	 * runs a simple syntax verification
	 * @param array the lines of the given file
	 * @return true if the file is legal
	 * @throws Compilation Exception if the syntax is illegal
	 */
	public static boolean isSyntaxLegal(ArrayList<String> array){
		return (checkBrackets(array)&&checkSemiColon(array)&&checkComment(array));
	}
	/*
	 * check the number of opener and closer brackets
	 */
	private static boolean checkBrackets(ArrayList<String> array){
		int opener = 0;
		int closer = 0;
		for (int i = 0 ; i<array.size();i++){
			if (array.get(i).contains(RegExp.OPENER1)){
				opener++;
			}
			if (array.get(i).contains(RegExp.CLOSER1)){
				closer++;
			}
		}
		if (closer==opener){
			return true;
		}
		return false;
	}

	/*
	 * check that lines without brackets end with ;
	 */
	private static boolean checkSemiColon(ArrayList<String> array){
		for (int i = 0 ; i<array.size();i++){
			if ((!array.get(i).contains(RegExp.OPENER1))&&(!array.get(i).contains(RegExp.CLOSER1))){
				if (!array.get(i).trim().endsWith(RegExp.SEMI_COL)){
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * look for illegal comment syntax
	 */
	private static boolean checkComment(ArrayList<String> array){
		for (int i = 0 ; i<array.size();i++) {
			if ((array.get(i).contains(RegExp.COM_OPENER)||array.get(i).contains(RegExp.COM_CLOSER)||
					array.get(i).contains(RegExp.JAVA_DOC)||array.get(i).contains(RegExp.COMMENT))){
				return false;
			}
		}
		return true;
	}
}
