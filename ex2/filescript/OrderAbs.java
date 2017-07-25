package clids.ex2.filescript;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public class OrderAbs implements Order {

	public TreeSet<File> order(ArrayList<File> array) {
		TreeSet<File> tree = new TreeSet<File>(new CompareAbs());
		for(int i = 0; i<array.size(); i++){
			tree.add(array.get(i));	
		}
		return tree;
//		System.out.println(tree.first()+ " "+tree.last());
	}
	
//	public static void main(String[] args) {
//		File lol = new File("lol");
//		File dag = new File("dag");
//		File loli = new File("Loli");		
//		File[] arr = new File[3];
//		arr[0] = lol;
//		arr[1] = dag;
//		arr[2] = loli;
//		order(arr);
//	}
	

}
