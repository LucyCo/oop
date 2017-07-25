package clids.ex4.variable;

/**
 * This object contains information about each of the variables
 */
public class Variable {
	//the variable value
	public String value;
	//the variable type (int,double,String,char or boolean)
	public String type;
	//true if it's a final variable false otherwise
	public boolean isFinal;
	//the name of the variable
	public String name;
	//true if the given variable is a method declaration parameter
	public boolean isMethodDecParam;
}
