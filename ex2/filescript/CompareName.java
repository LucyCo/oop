package clids.ex2.filescript;

import java.io.File;
import java.util.Comparator;

public class CompareName implements Comparator<File> {

	public int compare(File o1, File o2) {
		if (o1.getName().compareTo(o2.getName()) == 0) {
			return new CompareAbs().compare(o1, o2);
		}
		return o1.getName().compareTo(o2.getName());
	}
}
