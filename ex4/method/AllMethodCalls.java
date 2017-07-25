package clids.ex4.method;
import java.util.ArrayList;
import java.util.LinkedList;

import clids.ex4.toolbox.RegExp;
/**
 * This class receives an array of String represents method calls 
 * makes them method calls objects.
 * 
 * 
 * @author laurencohen and alonaoz
 *
 */
public class AllMethodCalls {
	/**
	 * Receives a string array of method calls and returns an array of object method call
	 * @param methodCalls string array list of method calls
	 * @return array list of object method call
	 */
	public static LinkedList<MethodCall> methodCalls(ArrayList<String> methodCalls) {
		LinkedList<MethodCall> allMethodCalls = new LinkedList<MethodCall>();
		MethodCall methodCall;
		for(int i=0; i<methodCalls.size(); i++) {
			String current = methodCalls.get(i);
			methodCall = new MethodCall(current.substring(0, current.indexOf(RegExp.OPENER)).trim());
			current = current.substring(current.indexOf(RegExp.OPENER)+1, current.lastIndexOf(RegExp.CLOSER));
			if(current.contains(RegExp.COMMA_REGEX)){
				String[] str = current.split(RegExp.COMMA_REGEX);
				for(int k=0; k<str.length; k++) {
					methodCall.add(str[k].trim());
				}
			}
			else {
				if(current.length()>0) {
					methodCall.add(current);
				}
			}
			allMethodCalls.add(methodCall);
		}
		return allMethodCalls;
	}
}
