package clids.ex2.filescript;

import java.io.File;
import java.util.Comparator;

public class CompareSize implements Comparator<File> {

	public int compare(File o1, File o2) {
		if (o1.length() > o2.length()) {
			return 1;
		}
		if (o1.length() < o2.length()) {
			return -1;
		}
		return new CompareAbs().compare(o1, o2);
	}
}