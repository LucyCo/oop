package clids.ex2.filescript;

import java.io.File;
import java.util.Comparator;

public class CompareMod implements Comparator<File> {

	@Override
	public int compare(File o1, File o2) {
		if (o1.lastModified() > o2.lastModified()) {
			return 1;
		}
		if (o1.lastModified() < o2.lastModified()) {
			return -1;
		}
		return new CompareAbs().compare(o1, o2);
	}
}
