package clids.ex2.filescript;

import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * This class determines if a given file belongs to an "before" filter
 *
 * @author alonaoz and lauren
 *
 */
public class Before extends Filter {

        /**
         * This method gets a file and a String represents a date and
         * return true if the given file was modified before the given date
         */
        public boolean isMatch(File file, String param1, String param2){
                //assign the last modification date to a long variable
                long lastModified = file.lastModified();
                //convert the long variable to a date object
                Date a = new Date(lastModified);
                //the format of the date
                String DATE_FORMATE = "dd/MM/yyyy";
                //convert the input string into a date object
                Date b = null;
                try {
                        b = new SimpleDateFormat(DATE_FORMATE).parse(param1);
                } catch (ParseException e) {
                        e.printStackTrace();
                }
                //compare the two date objects
                if(b.after(a)) {
                        isMatch = true;
                }
                else {
                        isMatch = false;
                }
                if(param2!=null){
                        if(param2.equals("NOT")) {
                                return !isMatch;
                        }
                }
                return isMatch;
        }
}