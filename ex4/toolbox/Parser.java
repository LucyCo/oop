package clids.ex4.toolbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import clids.ex4.exception.CompilationException;
/**
 * This class scan the file's lines and put them in an ArrayList
 * while ignoring the file's comments.
 * 
 * @author laurencohen and alonaoz
 *
 */
public class Parser {
	final static String exception = "Illegal variable declaration";
	/**
	 * transfers the file to an array list, ignoring comment and empty lines
	 * @param fileName name of the given file
	 * @return true if the parsing is complete
	 * @throws CompilationException if an illegal syntax is found
	 * @throws FileNotFoundException if the file is not found
	 */
	public static ArrayList<String> parser(File fileName) throws CompilationException, FileNotFoundException {
		ArrayList<String> fileInList = new ArrayList<String>();
		Scanner scan = new Scanner(fileName);
		while (scan.hasNext()){
			fileInList.add(scan.nextLine());
		}
		scan.close();
		return cleanFile(fileInList);
	}
	/*
	 * transfers each line to a simple sjava code syntax
	 */
	private static ArrayList<String> cleanFile(ArrayList<String> array) throws CompilationException {
		ArrayList<String> arrayNoComments = new ArrayList<String>();
		for (int i= 0; i<array.size(); i++){
			if (!array.get(i).startsWith(RegExp.COMMENT)&&!array.get(i).matches(RegExp.SPACE)){
				if(array.get(i).contains(RegExp.COMMA_REGEX)&&!array.get(i).contains(RegExp.OPENER)){
					checkCommaVariables(arrayNoComments, array.get(i));
					continue;
				}
				if(array.get(i).contains(RegExp.SEMI_COL)){
					while(((array.get(i).trim().indexOf(RegExp.SEMI_COL))!=(array.get(i).trim().length()
							-1))&&(array.get(i).contains(RegExp.SEMI_COL))) {
						String firstExp = array.get(i).substring(0, array.get(i).indexOf(RegExp.SEMI_COL)+1);
						String secExp = array.get(i).substring(array.get(i).indexOf(RegExp.SEMI_COL)+1, 
								array.get(i).length());
						arrayNoComments.add(firstExp.trim());
						array.set(i, secExp);
					}
					arrayNoComments.add(array.get(i).trim());
				}
				else {
					arrayNoComments.add(array.get(i).trim());
				}
			}
		}
		return arrayNoComments;
	}
	private static void checkCommaVariables(ArrayList<String> array, String str) throws CompilationException {
		if(str.matches(RegExp.VarReg)||str.matches(RegExp.VarReg2)){
			String str1 = str.substring(0, str.indexOf(RegExp.SPACE2));
			while(str.contains(RegExp.COMMA_REGEX)){
				String str2 = str.substring(str.lastIndexOf(RegExp.COMMA_REGEX)+1, str.length());
				str = str.substring(0, str.lastIndexOf(RegExp.COMMA_REGEX));
				array.add(str1+str2+RegExp.SEMI_COL);
			}
			array.add(str+RegExp.SEMI_COL);
		}
		else {
			throw new CompilationException(exception);
		}
	}
}