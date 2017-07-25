package clids.ex2.filescript;
import java.io.File;

public class PrintName extends Action {
	
	@Override
	protected void action(File file, String str) {
		System.out.println(file.getName());
	}
}
