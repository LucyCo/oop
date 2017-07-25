package clids.ex2.filescript;

/**
 * This class gets a filter String check it's value and return the Filter object
 * the given value represents
 * 
 * @author alonaoz and lauren
 * 
 */
public class FilterFactory {

	public static Filter createFilter(String filterString) throws Exception {
		// switch
		switch (filterString) {
		case ("before"):
			return new Before();
		case ("after"):
			return new After();
		case ("greater_than"):
			return new GreaterThan();
		case ("between"):
			return new Between();
		case ("smaller_than"):
			return new SmallerThan();
		case ("file"):
			return new FileName();
		case ("prefix"):
			return new Prefix();
		case ("suffix"):
			return new Suffix();
		case ("writable"):
			return new Writable1();
		case ("executable"):
			return new Executable1();
		case ("hidden"):
			return new Hidden();
		default:
			throw new Exception();
		}
	}
}
