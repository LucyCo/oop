package clids.ex1.analysis;
import java.util.LinkedList;
import java.util.TreeSet;
import clids.ex1.data_structures.MyHashSet;
import java.util.Date;
import java.util.Scanner;

/**
 * This is the class and the methods I used in order to analyze the time it takes 
 * (in milliseconds) to perform certain actions on three different data structures 
 * (TreeSet, LinkedList, HashSet (my implementation)).
 * @author laurencohen
 *
 */
public class MyAnalysis {
	private static LinkedList<String> list = new LinkedList<String>();
	private static TreeSet<String> treeSet = new TreeSet<String>();
	private static MyHashSet hashSet = new MyHashSet();
	private static String data1  = "data1.txt";
	private static String data2 = "data2.txt";
	private static String hash = "HashSet";
	private static String linkedList = "LinkedList";
	private static String tree = "TreeSet";
	
	/**
	 * The main method used to allow the user to check the run time for add() and contains()
	 * method in three different data structures (TreeSet, HashSet, LinkedList).
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			System.out.println("Please select the action you would like to perform:");
			System.out.println("1) Run the add() method for HashSet, TreeSet and LinkedList.");
			System.out.println("2) Run the contains() method for HashSet, TreeSet and LinkedList" +
					" (choose this option only after choosing option 1).");
			System.out.println("3) Regenerate structures.");
			System.out.println("4) Close.");
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			int x = scan.nextInt();
			switch(x) {
				case 1:
				System.out.println("Which data file would you like to test? " +
						"(enter 1 for data1 and 2 for data2)");
				int y = scan.nextInt();
				if (y==1) {
					addData(data1);
					break;
				}
				if (y==2) {
					addData(data2);
					break;
				}
				else {
					System.out.println("That wasn't an option.");
					break;
				}
				case 2:
				if (hashSet.size()==0) {
					System.out.println("You need to choose option 1 first!");
					break;
				}
				System.out.println("Which string value would you like to look for?");
				String string = scan.next();
				contains(string);
				break;
				case 3:
				System.out.println("Creating new data structures...");	
				list = new LinkedList<String>();
				treeSet = new TreeSet<String>();
				hashSet = new MyHashSet();
				break;
				case 4:
				System.out.println("Shutting down...");
				scan.close();
				return;
			}
		}
	}
	

	/**
	 * This method prints the run time for each of the structures to complete adding the values
	 * in the given data file.
	 * @param data the data file (data1.txt or data2.txt) that is to be added to each of 
	 * the structures.
	 */
	public static void addData(String data){
		System.out.println("Running .add for " + data+"...");
		addData(data, hash);
		addData(data, linkedList);
		addData(data, tree);		
	}
	
	/**
	 * This method prints out whether the given string value is found in each of the structures
	 * and how long (in milliseconds) it took to complete this action.
	 * Note that this option should be chosen only after adding files to the structures.
	 * @param str the given string to check in the current instance of each of the structures.
	 */
	public static void contains(String str){
		contains(str, hash);
		contains(str, linkedList);
		contains(str, tree);
	}
	
	private static void contains(String str, String datastr) {
		System.out.println("Running .contains for " + datastr+"...");
		long timeBefore = new Date().getTime();
		boolean contains = false;
		switch(datastr){
			case "HashSet":
			contains = hashSet.contains(str);
			break;
			case "LinkedList":
			contains = hashSet.contains(str);
			break;	
			case "TreeSet":
			contains = hashSet.contains(str);
			break;
			default:
			System.out.println("Unknown datastr.");
			break;
		}
		long timeAfter = new Date().getTime();
		if (contains) {
			System.out.println("The string "+str+" is found in " + datastr+"!");
		}
		else {
			System.out.println("The string "+str+" is not found in " + datastr+"!");
		}
		System.out.println("The action took "+(timeAfter-timeBefore)+" milliseconds " +
				"to perform.");
	}
	
	private static void addData(String data, String datastr) {
		String[] data1array = Ex1Utils.file2array(data);
		long timeBefore = new Date().getTime();
		for (int i = 0; i<data1array.length; i++) {
			switch(datastr){
				case "HashSet":
				hashSet.add(data1array[i]);
				break;
				case "LinkedList":
				list.add(data1array[i]);
				break;	
				case "TreeSet":
				treeSet.add(data1array[i]);
				break;
				default:
				System.out.println("Unknown datastr.");
				break;
			}
		}
		long timeAfter = new Date().getTime();
		System.out.println(datastr+": The action took "+(timeAfter-timeBefore)+
				" milliseconds to perform.");
	}
}
