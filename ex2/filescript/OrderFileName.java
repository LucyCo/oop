package clids.ex2.filescript;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.File;

public class OrderFileName implements Order{

	public TreeSet<File> order(ArrayList<File> array) {
		TreeSet<File> tree = new TreeSet<File>(new CompareName());
		for(int i = 0; i<array.size(); i++){
			tree.add(array.get(i));	
		}
		return tree;
	}
}
