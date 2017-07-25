package clids.ex2.filescript;

import java.io.File;
import java.util.Comparator;

public class CompareAbs implements Comparator<File> {

	public int compare(File o1, File o2) {

		if (o1.getAbsolutePath().compareTo(o2.getAbsolutePath()) == 0) {
			if (Character.isUpperCase(o1.getName().charAt(0))
					&& Character.isUpperCase(o2.getName().charAt(0))) {
				return 0;
			}
			if (Character.isUpperCase(o1.getName().charAt(0))
					&& (!Character.isUpperCase(o2.getName().charAt(0)))) {
				return 1;
			} else {
				return -1;
			}

		}
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
}