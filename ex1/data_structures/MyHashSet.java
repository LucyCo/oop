package clids.ex1.data_structures;
import java.util.LinkedList;


public class MyHashSet {
	@SuppressWarnings("rawtypes")
	LinkedList[] list;
	protected int capacity;
	protected static double upperLoadFactor;
	protected static double lowerLoadFactor;
	protected double loadFactor;
	protected int size;
	//constructors
	/**
	* A default constructor.
	* Constructs a new, empty table with default initial capacity (16) * and load factor 
	* (0.75) and lower load factor (0.25).
	*/
	
	public MyHashSet() {
		capacity = 16;
		upperLoadFactor = 0.75;
		lowerLoadFactor = 0.25;
		size = 0;
		list = new LinkedList[capacity];
		for(int i = 0; i<list.length; i++) {
			list[i] = new LinkedList<String>();
		}
	}
	
	/**
	* Constructs a new, empty table with the specified initial capacity 
	* and the specified load factors.
	* @param initialCapacity - the initial capacity of the hash table. 
	* @param upperLoadFactor - the upper load factor of the hash table. 
	* @param lowerLoadFactor - the lower load factor of the hash table. 
	*/
	public MyHashSet(int initialCapacity, double upperLoadFactor, double lowerLoadFactor){
		capacity = initialCapacity;
		MyHashSet.upperLoadFactor = upperLoadFactor;
		MyHashSet.lowerLoadFactor = lowerLoadFactor;
		size = 0;
		list = new LinkedList[capacity];
		for(int i = 0; i<list.length; i++) {
			list[i] = new LinkedList<String>();
		}
	}
	
	/**
	* Data constructor - builds the hash set by adding the elements 
	* into the input array one-by-one.
	* If the same value appears twice (or more) in the list, it is 
	* ignored.
	* The new table has the default values of initial capacity
	* (16), upper load factor (0.75), and lower load factor (0.25). 
	* @param data Values to add to the set.
	*/
	public MyHashSet(String[] data){
		this();
		for(int i = 0; i<data.length; i++) {
			this.add(data[i]);
		}
	}

	//methods
	/**
	* Add a new element with value newValue into the table.
	* @param newValue new value to add to the table.
	* @return false iff newValue already exists in the table. */
	@SuppressWarnings("unchecked")
	public boolean add(String newValue){
		if (this.contains(newValue)) return false;
		list[Math.abs(newValue.hashCode()%capacity)].add(newValue);
		++size;
		loadFactor = (double)size/(double)capacity;
		if(loadFactor>upperLoadFactor){
			resize();
		}
		return true;
	}
	
	/**
	* Look for an input value in the table.
	* @param searchVal value to search for.
	* @return true iff searchVal is found in the table. */
	public boolean contains(String searchVal){
		if(list[Math.abs(searchVal.hashCode()%capacity)].contains(searchVal)) return true;
		return false;
	}
	
	/**
	* Remove the input element form the table.
	* @param toDelete value to delete.
	* @return true iff toDelete is found and deleted. */
	public boolean delete(String toDelete){
		int index = Math.abs(toDelete.hashCode()%capacity);
		for(int i = 0; i<list[index].size(); i++) {
			if(toDelete.equals(list[index].get(i))) {
				list[index].remove(i);
				--size;
				loadFactor = (double)size/(double)capacity;
				if(loadFactor<lowerLoadFactor){
					resize();
				}
				return true;	
			}		
		}
		return false;
	}
	
	/**
	* @return the number of elements in the table. */
	public int size(){
		return size;
	}
	
	/**
	* @return the capacity of the table. */
	public int capacity(){
		return capacity;
	}
	
	private void change(MyHashSet tmp) {
		capacity=tmp.capacity;
		loadFactor=tmp.loadFactor;
		list=tmp.list;
		tmp=null;	
	}
	
	protected void resize(){
		if(loadFactor>upperLoadFactor) {
			MyHashSet tmp = new MyHashSet(capacity*2, upperLoadFactor, lowerLoadFactor);
			for(int i = 0; i<list.length; i++){
				for(int k = 0; k<list[i].size(); k++){
					tmp.add((String)list[i].get(k));
				}
			}
			change(tmp);
		}
		if(loadFactor<lowerLoadFactor) {
			MyHashSet tmp = new MyHashSet(capacity/2, upperLoadFactor, lowerLoadFactor);
			for(int i = 0; i<list.length; i++){
				for(int k = 0; k<list[i].size(); k++){
					tmp.add((String)list[i].get(k));
				}
			}
			change(tmp);
		}
	}
}
