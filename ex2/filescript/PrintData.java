package clids.ex2.filescript;
import java.io.*;
import java.util.Date;
public class PrintData extends Action{
	
	@Override
	protected void action(File file, String str) throws Exception {
		long lastModified = file.lastModified();
		Date mod = new Date(lastModified);
		System.out.print(file.isHidden() ? "h" : "-");
		System.out.print(file.canWrite() ? "w" : "-");
		System.out.print(file.canExecute() ? "x" : "-");
		System.out.println(" " + (double)file.length()/1024+" "+mod.toString()+ " "+file.getAbsolutePath());
	}
}
