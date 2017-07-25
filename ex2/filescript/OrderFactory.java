package clids.ex2.filescript;

public class OrderFactory {

	public Order factory(String str) throws Exception {
		switch (str) {
		case "abs":
			return new OrderAbs();
		case "file":
			return new OrderFileName();
		case "mod":
			return new OrderModified();
		case "size":
			return new OrderSize();
		default:
			throw new Exception();
		}
	}
}
