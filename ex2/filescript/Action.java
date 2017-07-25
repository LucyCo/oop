package clids.ex2.filescript;
import java.io.File;
import java.util.TreeSet;

public abstract class Action {
	
	public void action(TreeSet<File> tree, String param1, String param2) throws Exception {
		File[] file = tree.toArray(new File[tree.size()]);
		int i = 0;
		while(i<file.length) {
			try{
				File current = file[i];
				action(current, param1);
			}catch (Exception e){
				throw new Exception();
			}
			i++;
		}
	}
		protected abstract void action(File file, String str) throws Exception;
}