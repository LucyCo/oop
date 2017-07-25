package clids.ex2.filescript;

import java.io.*;

public class MyFileScript {

	public static void main(String[] args) throws Exception {
		File commandFile = new File(args[1]);
		Parsing parse = new Parsing(commandFile, args[0]);
		try{
			Parsing.reading(parse.temp); 
		}
		catch(Exception e){
			System.err.println("ERROR");
		}
	}
}
