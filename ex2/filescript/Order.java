package clids.ex2.filescript;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public interface Order {
	public TreeSet<File> order(ArrayList<File> array);
}
