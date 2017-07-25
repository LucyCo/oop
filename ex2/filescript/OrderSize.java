package clids.ex2.filescript;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public class OrderSize implements Order {

	public TreeSet<File> order(ArrayList<File> array) {
		TreeSet<File> tree = new TreeSet<File>(new CompareSize());
		for(int i = 0; i<array.size(); i++){
			tree.add(array.get(i));	
		}
		return tree;
	}
	
//	public static void main(String[] args) {
//	File lol = new File("lol");
//	File dag = new File("dag");
//	File lulu = new File("lulu");
//	File[] arr = new File[3];
//	arr[0] = dag;
//	arr[1] = lulu;
//	arr[2] = lol;
//	order(arr);		
//	}
}