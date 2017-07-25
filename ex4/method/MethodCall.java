package clids.ex4.method;
import java.util.ArrayList;

/**
 * an object of type method call
 * @param methodName the name of the method
 * @param parameters the given parameters of the call
 */
public class MethodCall {
	public String methodName;
	public ArrayList<String> parameters;
	/**
	 * constructor
	 * @param name the name of the method
	 */
	public MethodCall(String name){
		this.methodName = name;
		parameters = new ArrayList<String>();
	}
	/**
	 * add a parameter
	 * @param param the given parameters
	 */
	public void add(String param) {
		parameters.add(param);
	}
}
