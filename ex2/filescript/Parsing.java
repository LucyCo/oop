package clids.ex2.filescript;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * 
 * @author alonaoz and laurencohen
 *
 */
public class Parsing {
	Queue<String> q ;
	public Queue<String> temp ;
	static File srcDir;
	static ArrayList<String> arrayComments;
	private static int number = 0;
	static int filterErrorLine=0;

	public Parsing(File commandFile, String sourceDir) throws Exception{
		Parsing.srcDir = new File(sourceDir);
		q = new LinkedList<String>();
		String line;
		Scanner scan = new Scanner(commandFile);
		while (scan.hasNext()) {
			line = scan.nextLine();
			q.add(line);
		}
		scan.close();
		temp = q;
	}
	public static void reading(Queue<String> temp) throws Exception{
		ArrayList<String> array;
		String current = temp.remove();
		if (!current.equals("FILTER")){
			throw new Exception();
		}
		while(current.equals("FILTER")&&(!temp.isEmpty())){
			array = new ArrayList<String>();
			arrayComments= new ArrayList<String>();
			array.add(current);
			current = temp.remove();
			while(!current.equals("FILTER")){
				if(current.startsWith("@")){
					arrayComments.add(current);
				}
				array.add(current);
				current=temp.remove();
				if(temp.isEmpty()){
					array.add(current);
					break;
				}
			}
			try{
				cleanErrorII(array);
			}catch (Exception e){
				throw new Exception();
			}
			ArrayList<File> filtered = startFilter(array);
			TreeSet<File> tree = startOrder(array,filtered);
			startAction(tree,array);
			number = number+array.size();
		}	
	}

	private static ArrayList<File> startFilter(ArrayList<String> filterArray){
		int number1 = number;
		number1++;
		String filty=null;
		Filter filter1;
		ArrayList<File> arry;
		while(!filterArray.get(number1).equals("ACTION")){
			if ((filterArray.get(number1)).startsWith("@")){
				number1++;
			}
			else{
				filty = filterArray.get(number1);
				break;
			}
		}
		if (filty==null){
			return allFiles(srcDir);
		}
		else{//if filter is on the third line of the file we pull it later on check it
			String[] filterString = dividedString(filty);
			try{
				filter1 = FilterFactory.createFilter(filterString[0]);
			}
			catch(Exception e){
				System.err.println("Warning in line "+ (number+(filterArray.indexOf(filty)+1)));
				return allFiles(srcDir);
			}

			try{
				arry = filter1.filter(srcDir,filterString[1],filterString[2]);
			}
			catch(Exception e) {
				System.err.println("Warning in line "+ (number+(filterArray.indexOf(filty)+1)));
				return allFiles(srcDir);
			}
			return arry;	
		}	
	}
	private static TreeSet<File> startOrder(ArrayList<String> orderArray, ArrayList<File> filtered){
		Order order1;
		OrderFactory factory = new OrderFactory();
		if (!orderArray.contains("ORDER")){
			order1 = new OrderAbs();
			return order1.order(filtered); 
		}
		int number1= (orderArray.indexOf("ORDER")+1);
		String filty = null;
		while(number1<orderArray.size()){
			if ((orderArray.get(number1)).startsWith("@")){
				number1++;
			}
			else{
				filty = orderArray.get(number1);
				break;
			}
		}
		if (filty==null){
			order1 = new OrderAbs();
			return order1.order(filtered);
		}
		String[] str= dividedString(filty);
		try {
			order1= factory.factory(str[0]);
		}
		catch (Exception e){
			System.err.println("Warning in line "+ (number+orderArray.indexOf(filty)+1));
			order1 = new OrderAbs();
			return order1.order(filtered); 
		}
		return order1.order(filtered);
	}
	private static void startAction(TreeSet<File> tree, ArrayList<String> actionArray){
		TreeSet<File> treeCopy;
		String filty=null;
		int index = actionArray.indexOf("ACTION"); 
		String[] str = null;
		index++;
		boolean alreadyPrinted = false;
		while(index<actionArray.size()){
			if(actionArray.get(index).equals("ORDER")){
				break;
			}
			if (!actionArray.get(index).startsWith("@")){
				treeCopy = tree;
				filty = actionArray.get(index);
				str = dividedString(filty);
				Action action1;

				try{
					action1 = new ActionFactory().factory(str[0]);

				}
				catch (Exception e){
					System.err.println("Warning in line "+(number+actionArray.indexOf(filty)+1));
					index++;
					continue;
				}
				if(!alreadyPrinted){
					printComments(arrayComments);
					alreadyPrinted=true;
				}
				try{
					action1.action(treeCopy, str[1], str[2]);
				}
				catch (Exception e){
					System.err.println("Action failed in line "+(number+actionArray.indexOf(filty)+1));
					index++;
					continue;
				}
			}
			index++;
		}
		return;
	}
	private static void printComments(ArrayList<String> array){
		for(int i= 0 ; i<array.size(); i++){
			System.out.println(array.get(i));
		}
	}
	private static ArrayList<File> allFiles(File srcDir){
		ArrayList<File> allFiles = new ArrayList<File>();
		if(srcDir.isDirectory()){
			File[] children = srcDir.listFiles();
			if(children == null) return allFiles;
			for(int i =0; i<children.length; i++){
				allFiles = allFiles(children[i]);
			}
		}
		else {
			allFiles.add(srcDir);
		}
		return allFiles;
	}
	private static void cleanErrorII(ArrayList<String> section) throws Exception{
		if (!section.contains("ACTION")){
			throw new Exception();
		}
		if (!moreThanOne(section,"ACTION")||(!moreThanOne(section,"ORDER"))){
			throw new Exception();
		}
		if(section.contains("ORDER")){
			int action_location = section.indexOf("ACTION");
			int order_location= section.indexOf("ORDER");
			if (action_location>order_location){
				System.out.println("here");
				throw new Exception();
			}
		}
	}
	private static boolean moreThanOne(ArrayList<String> array, String command){
		int num = 0;
		for(int i = 0; i<array.size(); i++){
			if (command.equals(array.get(i))){
				num++;	
			}
		}
		return (num<=1);
	}

	/**
	 * divide the given string to an array of substrings
	 * 
	 * @param string
	 * @return
	 */
	private static String[] dividedString(String string) {
		String[] dividedStr = new String[3];
		int i = 0;
		while(string.contains("%")&&dividedStr.length<=3){
			dividedStr[i] = string.substring(0, string.indexOf('%'));
			string = string.substring(string.indexOf('%')+1, string.length());
			++i;
		}
		dividedStr[i] = string;
		return dividedStr;
	}
}
