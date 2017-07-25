package clids.ex2.filescript;
import java.io.*;
import java.util.ArrayList;

public abstract class Filter {
	protected ArrayList<File> files;
	protected ArrayList<File> filteredFiles;
	protected boolean isMatch = false;
	
	protected abstract boolean isMatch(File file, String param1, String param2) throws Exception;

	public ArrayList<File> filter(File srcDir, String param1, String param2) throws Exception {
		files = new ArrayList<File>();
		filteredFiles = new ArrayList<File>();
		File current = srcDir;
		files = getFiles(current, files);
		if (files!=null){
			for(int i = 0; i<files.size(); i++){
				try{
					if(isMatch(files.get(i), param1, param2)){
					filteredFiles.add(files.get(i));
					}
				}catch(Exception e){
					throw new Exception();
				}
			}
			if(filteredFiles==null){
				filteredFiles=files;
			}
		}
		else{
			throw new Exception("empty directory");
		}
		return filteredFiles;
	}
	
	private ArrayList<File> getFiles(File current, ArrayList<File> files){
		if(current.isDirectory()){
			File[] children = current.listFiles();
			if(children == null) return files;
			else {
				for(int i = 0; i<children.length; i++){
					files = getFiles(children[i], files);
				}
			}
		}
		else {
			files.add(current);
		}
		return files;
	}
}
