package clids.ex4.toolbox;
import java.util.regex.Pattern;
/**
 * contains a bank of regular expressions the compiler uses.
 * 
 * @author laurencohen alonaoz
 */
public class RegExp {
	public RegExp(){}
	//an empty string
	public final static String emptyStr = "";
	//a String represents a space used when we want to split it
	public final static String emptyStrSplit = "[ ]";
	//a String represents the "and" or "or" conditions
	public final static String insidePar = "&&|\\|\\|";
	//a String represents the beginnig of a comments
	public final static String COMMENT = "//";	
	//a String represents the beginnig of a comment
	public final static String COM_OPENER = "/*";
	//a String represents the end of a comment
	public final static String COM_CLOSER = "*/";
	//a String represent the beginning of a java doc comment.
	public final static String JAVA_DOC = "/**";
	//a String represent semicolon
	public final static String SEMI_COL = ";";
	//a String represent a dot char
	public final static String CHAR = "'.'";
	//a String represents true of false or zero or more other character
	public final static String BOOL = "(true|false)|[-0-9.]*";
	//a string represents any kind of String inside a quotation
	public final static String STRING = "\".*\"";
	//a String represents a double number
	public final static String DOUBLE = "[-0-9.]*";
	//a String represents an integer number
	public final static String INT = "[-0-9]*";
	//a String represents a curly parenthesis opener
	public final static String OPENER1 = "{";
	//a String represents a curly parenthesis closer
	public final static String CLOSER1 = "}";
	//a String represents a parenthesis opener
	public final static String OPENER = "(";
	//a String represents a parenthesis closer
	public final static String CLOSER = ")";
	//a String represents zero or more spaces
	public final static String SPACE = "\\s*";
	//a String represents a single space
	public final static String SPACE2 =	" ";
	//a String represents a comma
	public final static String COMMA_REGEX = ",";
	//a String represents the word : return followed by semicolon
	public final static String RETURN = "return;";	
	//a String represents the inner content of the parenthesis of a method declaration  
	public final static String INNER = "((final)\\s+)?(int|String|double|char|boolean)\\s+[a-z][a-zA-Z0-9_]*$";
	//a String represents the word "if"
	public final static String IF = "if";
	//a String represents the word: "while"
	public final static String WHILE = "while";
	//a String represents the word: "void"
	public final static String VOID = "void";
	//a String represents a line where there are two members of variables
	public final static String splitVar = "[ =;]+";	
	//a String represents a comma that may appear within variables
	public final static String splitVarName = "[, ]";
	//a String represents parameters that may be split 
	public final static String getParamsSplit = "[ (),{]+";
	//a String represents a variable declaration 
	public final static String VarReg2 = "(double|String|int|char|boolean)\\s+[a-z][a-zA-Z0-9_]*\\s*=\\s*" +
	"[0-9]*\\s*,\\s*[a-z]\\s*=*\\s*[0-9]*;";
	//a String represents  a variable declaration that might be separated by commas
	public final static String VarReg = "(double|String|int|char|boolean)\\s+[a-z][a-zA-Z0-9_]*\\s*(,\\s*" +
			"[a-z]\\s*)+?;";
	//a String represents a variable type
	public final static String VAR_TYPE_REGEX = "(int|double|String|boolean|char)";
	//a String represents a variable assignment
	public final static String VAR_ASSIGNMENT_REGEX =
            "((?:[A-Za-z]+[A-Za-z0-9_]*)|(?:_[A-Za-z0-9_]+))\\s*(?:=\\s*(.*?));";
	//a String represents a method declaration 
	public final static String METHOD_DECLARATION_REGEX =
            "void\\s*([A-Za-z]+[A-Za-z0-9_]*)\\s*\\((.*?)\\)\\s*\\{";
	//a String represents the parameters inside a method declaration
	public final static String  METHOD_PARAMETERS_REGEX =
            "(final)?\\s*(int|double|String|boolean|char)\\s*([^,])\\s*(?:,|$)";
	//a String represents the if command condition
	public final static String IF_BLOCK_REGEX = "[ \\t]*if[ \\t*]*\\((.*?)\\)[ \\t]*\\{";
	//a String represents the while command condition
	public final static String WHILE_BLOCK_REGEX = "[ \\t]*while[ \\t*]*\\((.*?)\\)[ \\t]*\\{";
	//a String represent a method call
	public final static String METHOD_CALL_REGEX =
			"[ \\t]*((?:[A-Za-z]*[A-Za-z0-9_]*)|(?:_[A-Za-z0-9_]*))[ \\t]*\\((.*)\\)[ \\t]*;";
	//a String represents a method return statement 
	public final static String METHOD_RETURN_REGEX = "[\\r\\n\\t ]*return[ \\t\\n\\r]*;[ \\t\\n\\r]*$";
	//a String represents a while if declaration that are true of false or a digit
	public final static String WHILE_IF_DECLARATION = "^\\s*(?:true|false|(?:\\d+\\.?\\d?))\\s*$";
	/*A conversion of the String represents the regular expression to a compiled patterns */
	public final static Pattern VAR_TYPE_REGEX_COMPILED = Pattern.compile(VAR_TYPE_REGEX);
	public final static Pattern VAR_ASSIGNMENT_COMPILED_REGEX = Pattern.compile(VAR_ASSIGNMENT_REGEX);
	public final static Pattern METHOD_DECLARATION_COMPILED_REGEX =  Pattern.compile(METHOD_DECLARATION_REGEX);
	public final static Pattern METHOD_PARAMETERS_COMPILED_REGEX =  Pattern.compile(METHOD_PARAMETERS_REGEX, 
			Pattern.MULTILINE);
	public final static Pattern IF_BLOCK_COMPILED_REGEX = Pattern.compile(IF_BLOCK_REGEX);
	public final static Pattern WHILE_BLOCK_COMPILED_REGEX = Pattern.compile(WHILE_BLOCK_REGEX);
	public final static Pattern METHOD_CALL_COMPILED_REGEX = Pattern.compile(METHOD_CALL_REGEX);
	public final static Pattern METHOD_RETURN_COMPILED_REGEX = Pattern.compile(METHOD_RETURN_REGEX);

}