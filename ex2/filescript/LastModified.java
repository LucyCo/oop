package clids.ex2.filescript;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastModified extends Action {
	
	@Override
	protected void action(File file, String str) throws Exception {
		if(str==null){
			throw new Exception();
		}
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    long dateInLong = date.getTime();
	    file.setLastModified(dateInLong);
	}
}
