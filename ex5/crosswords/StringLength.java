package clids.ex5.crosswords;

import java.util.Comparator;
/**
 * a comparator for comparing string objects according to their length
 */
public class StringLength implements Comparator<String> {
	/**
	 * implementation for the compare method
	 */
	public int compare(String o0, String o1) {
		return o1.length()-o0.length();
	}
}
