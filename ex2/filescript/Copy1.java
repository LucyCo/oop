package clids.ex2.filescript;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class Copy1 extends Action {

	protected void action(File file, String str) throws Exception {
		if(str==null){
			throw new Exception();
		}
		if(str.startsWith("/")){
			throw new Exception();
		}
		File target = new File (str+file.getName());

		try {
			copyFile(file.getAbsoluteFile(),target.getAbsoluteFile());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	private static void copyFile(File source, File dest) throws Exception {
		if(!dest.exists()) {
			dest.createNewFile();
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		}
		finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		}
	}


	//      public static void main(String[] args) throws IOException {
	//              File source = new File("dag");
	//
	//              String target = "ex2/clids/ex2/";
	//              action(source, target);
	//      }
}