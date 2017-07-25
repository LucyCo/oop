=============================
=  README for MyHashSet.java & MyAnalysis.java  =
=============================

usage: java MyAnalysis

==================
=  Description:  =
==================

A Set is a collection that contains no duplicate elements. More formally, for each two 
elements in the set, a and b, a.equals(b) returns false. This can be useful, for example, 
for representing a deck of cards or a mathematical set.
Hash set is a common Set implementation that provides efficient add(), contains() and delete()
 operations. Hash sets use a special function called hash function, which maps any objects 
 to a single integer, an index in the hash structure which is based on the object’s hash code.

The functions available for hashset are: 
add(String object) - adds the given string object to the list iff the object is not already
found in the list. returns true if the object was added, else, returns false.
delete(String object) - returns true if the object was found and removed, else, returns false.
contains(String object) - returns true if the object was found, else, returns false.
size() - returns int value of the number of objects currently in the hashset.
capacity() - returns int value of the number of cells currently in the hashset.

There are three constructors for the hashset:
MyHashSet() - default constructor, creating an instance of an empty hashset with the default 
settings; lowerLoadFactor - 0.25, upperLoadFactor - 0.75, initial capacity - 16.
MyHashSet(int initialCapacity, double upperLoadFactor, double lowerLoadFactor) - 
creates an instance of an empty hashset with the given parameters.
MyHashSet(String[] string) - creates an instance of hashset populated with the given data.

MyHashSet is a collection type class built on java's linkedlist. each cell contains an object
of a generic <string> linked list to avoid collisions.
 
The implementations of add() and delete():
The add() method, receives a given string value to add to the set. it sends the value to
the contains() method that checks whether the list in the math.abs(hashcode%capacity) index of the set
contains the same object. if the object is found in the set, it is ignored (and the add() method returns 
false). if not, it is added to the hash. after adding the object, the size and loadfactor are being updated.
if the load factor is higher than the upperloadfactor value, the set is recreated with a doubled
capacity and rehashed by the private resize() method. when it's finished, the add() method returns
true.

The delete() method, receives a given string value to remove from the set. first it runs a for
loop on the sub list that is found in the index the object should be placed in order to find the 
object. if the object is not found, the delete() method returns false.
If the object is found, it is removed from the set (as well as the cell of the linked list it 
was in). after removing, the size and loadfactor are being updated. if the loadfactor
is lower than the lowerloadfactor value, the set is recreated with a half sized capacity and
rehashed by the private resize() method. when it's finished, the delete() method returns true.

MyAnalysis.java is a class with a main method, built in order to examine time differences between
three figures of data structures (TreeSet, MyHashSet, LinkedList). The methods run actions on the
data structures' methods and prints the results for the time period each dAst takes in order to
complete the tasks.

=============================
=  Answers for MyAnalysis   =
=============================
Answer to question 1:
If the size is larger than capacity, size/capacity = loadfactor > 1. This means that there is
a large number of objects in the linked lists in each of the cells, and the number of cells is
small. Meaning that for each of the usually O(1)'ed actions, such as add(), remove(), contains(),
the searching for a given object will be done over long linked lists, in the worst case, one long
linked list at the size of all the n objects. causing the time complexity become O(n) in the worst
case.

If the capacity is much larger than the size, this should not affect the time complexity (O(1)), 
but it causes a waste of memory (due to large number of null cells).

In the general case, when the loadFactor is between 0.25-0.57 it causes a good balance between
the number of null cells and the number of objects in each cell, so the complexity of the actions
are based on giving a specific index value which is O(1) and searching for the object in a linked
list sized k. so the average action should take O(1+k).

Answer to question 2:
I made a table with the results of the run time:

						hashSet		linkedList		treeSet
					    ------------------------------------
addData1				 96066			3			  56
						------------------------------------
addData2				 119			2		   	  52
						------------------------------------
Data1 hi				  0				0			  0		not found
						------------------------------------
Data1 -13170890158		  1				1			  1		not found		
						------------------------------------
Data1 13170802079*		  1				1			  1		found		
						------------------------------------
Data2 hi				  0				0			  0		not found
						------------------------------------
Data2 23				  0				0			  0		found
						------------------------------------
Data2 -49999*			  0				0			  0		found

* Note that I also ran the contains method for the last string in each of the data files,
to emphasize the results.

.add for data1 :  there was a great time difference between the different data structures for 
adding the words in the data1 files. as you can see, it took far too long to add the entire string
array to MyHashSet seeing as all the strings were eventually mapped to the same cell. when trying
to use the add function after already having most of the strings mapped into the same cell, the contains
function runs all over the linked list inside the one cell. by that, increasing the rum time by far.
As for the java linked list and the java tree set, we are getting almost the same results.
it takes between 1-3 milliseconds for the linked list to map the strings and between 50-60
milliseconds for the tree set to map the strings.
.add for data2 : there isn't much of a time difference between the three data structures.
it takes around 100-150 milliseconds for MyHashSetto map the entire array. It takes half the time
for the tree set to map it (50-60), and 1-3 for java linked list.

time difference between data1 and data2: for the linked list and tree set there wasn't much of a difference
in time for adding the data files. but for MyHashSet, there was a large difference. 
While MyHashSet maps the strings in data1 file all in one cell, it maps the strings in data2
across the entire table, so the contains method in MyHashSet simply looks for the given string
in the table according to the index given by the hash code of the string instead of comparing
one string at a time. making the process much quicker.

.contains : There was no difference between the runtime for any of the structures,
I even tried searching for the last words in the files but all the structures found or didn't
find the given strings in 0-1 milliseconds.

=============================
=  List of submitted files: =
=============================

README           	This file
MyHashSet.java   	Implementation of the hash set.
MyAnalysis.java 	The methods I used to analyze the data structures.
Ex1Utils.java   	The file reader given by the course staff.

======================
=  Special Comments  =
======================

