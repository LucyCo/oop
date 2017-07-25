package clids.ex2.filescript;

public class ActionFactory {

	public Action factory(String str) throws Exception {
		switch (str) {
		case "print_data":
			return new PrintData();
		case "print_name":
			return new PrintName();
		case "copy":
			return new Copy1();
		case "exec":
			return new Executable();
		case "write":
			return new Writeable();
		case "last_mod":
			return new LastModified();
		default:
			throw new Exception();
		}
	}

	
}
