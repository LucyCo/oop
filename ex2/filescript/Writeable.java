package clids.ex2.filescript;
import java.io.File;

public class Writeable extends Action{

	@Override
	protected void action(File file, String str) throws Exception {
		if(str==null){
			throw new Exception();
		}
		boolean whattodo;
		if(str.equals("YES")){
			whattodo = true;
		}
		else if(str.equals("NO")){
			whattodo = false;
		}
		else{
			throw new Exception();
		}
		file.setWritable(whattodo);
	}
}
