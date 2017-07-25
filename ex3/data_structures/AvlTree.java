package clids.ex3.data_structures;

public class AvlTree {
	//the root of this tree
	public AvlNode root;
	
	/**
	* A default constructor. 
	*/
	public AvlTree(){}
	
	/**
	* A data constructor 
	* a constructor that builds the tree by adding the elements in the input array one-by-one.
	* If the same value appears twice (or more) in the list, it is ignored.
	* @param data values to add to tree 
	*/
	public AvlTree(int[] data){
		for(int i = 0; i<data.length; i++){
			this.add(data[i]);
		}
	}
	
	/**
	* Add a new node with value newValue into the tree.
	* @param newValue new value to add to the tree.
	* @return false iff newValue already exist in the tree
	*/
	public boolean add(int newValue) {
		//check whether or not this tree contains a node with this value
		if (contains(newValue)!=-1) { 
			return false;
		}
		AvlNode n = new AvlNode(newValue);
		add(this.root,n);
		return true;
	}
	
	/**
	* Does tree contain a given input value.
	* @param val value to search for.
	* @return if val is found in the tree, return the depth of its node (where 0 is the root).
	* Otherwise return -1. 
	*/
	public int contains(int searchVal){
		AvlNode current = root;
		current = this.contains(searchVal,current);
		if(current==null) {
			return -1;
		}
		return this.depth(current);
	}
	
	/**
	* Remove a node from the tree, if it exists.
	* @param toDelete value to delete
	* @return true iff toDelete is found and deleted 
	*/
	public boolean delete(int toDelete) {
		//check whether a node containing the given value is found
		AvlNode node = this.contains(toDelete, root);
		if (node==null) {
			return false;
		}
		//the node is found, remove it from the tree
		removeNode(node);
		return true;
	}
	
	/**
	* Returns the number of nodes in this tree
	* @return number of nodes in the tree 
	*/
    public int size() {
    	//run a recursive helper method to count the number of nodes in this tree
        return sizeRec(root);
    }
    
	/*
	 * searches the node containing the given value, returns the node if found, if not, 
	 * returns null.
	 */
	private AvlNode contains(int searchVal, AvlNode current){
		if (current==null) return null;
		if (searchVal < current.value) {
			//the value is smaller than current node's value, call this method for the left child
			//recursively
			return contains(searchVal, current.left);
		}
		else if(searchVal > current.value) {
			//the value is larger than current node's value, call this method for the right child
			//recursively
			return contains(searchVal, current.right);
		}
		return current;
	}
	
    /*
     * Recursively counts the number of nodes in this tree
     */
    private int sizeRec(AvlNode cur)  {
        if (cur==null) {
            return 0;
        } 
        else {
        	return 1 + (sizeRec(cur.left) + sizeRec(cur.right));
        }
    }
	
	/*
	 * a recursive method to add a node into a BST tree, after the insertion, check whether
	 * the balance had been interrupted.
	 */
	private void add(AvlNode cur, AvlNode newNode) {
		if (cur==null) {
			this.root=newNode;
		} 
		else {
			if (newNode.value < cur.value) {
				if (cur.left==null) {
					cur.left = newNode;
					newNode.parent = cur;
					//the node has been inserted, now check if the tree is still balanced.
					balance(cur);
				} 
				else {
					add(cur.left, newNode);
				}
			} 
			else if (newNode.value > cur.value) {
				if (cur.right==null) {
					cur.right = newNode;
					newNode.parent = cur;
					balance(cur);
					//the node has been inserted, now check if the tree is still balanced.
				} 
				else {
					add(cur.right, newNode);
				}
			} 
		}
	}
	
	/*
	 * this method checks whether the balance had been interrupted if it has, the method rotates
	 * the unbalanced node according to what we learned in class.
	 */
	private void balance(AvlNode cur) {
		setBalance(cur);
		int balance = cur.balance;
		if (balance==2) {
			//the balance is disturbed in the left child
			if (height(cur.left.left) >= height(cur.left.right)) {
				//the balance is disturbed in the left child of the left child
				cur = rotateRight(cur);
			} 
			else {
				//the balance is disturbed in the right child of the left child
				cur = doubleRotateLeftRight(cur);
			}
		} 
		else if (balance==-2) {
			//the balance is disturbed in the right child
			if (height(cur.right.right) >= height(cur.right.left)) {
				//the balance is disturbed in the right child of the right child
				cur = rotateLeft(cur);
			} 
			else {
				//the balance is disturbed in the left child of the right child
				cur = doubleRotateRightLeft(cur);
			}
		}
		if (cur.parent!=null) {
			//as long as we haven't reached the root, keep going
			balance(cur.parent);
		} 
		else {
			this.root = cur;
		}
		//else do nothing
	}

	/*
	 * Removes the given node, balancing will be done if necessary.
	 */
	private void removeNode(AvlNode cur) {
		AvlNode node1;
		if (cur.left==null || cur.right==null) {
			if (cur.parent==null) {
				this.root=null;
				cur = null;
				return;
			}
			node1 = cur;
		} 
		else {
			node1 = successor(cur);
			cur.value = node1.value;
		}
		AvlNode node2;
		if (node1.left!=null) {
			node2 = node1.left;
		} 
		else {
			node2 = node1.right;
		}
		if (node2!=null) {
			node2.parent = node1.parent;
		}
		if (node1.parent==null) {
			this.root = node2;
		} 
		else {
			if (node1==node1.parent.left) {
				node1.parent.left = node2;
			} 
			else {
				node1.parent.right = node2;
			}
			balance(node1.parent);
		}
		node1 = null;
	}
	
	/*
	 * Left rotation using the given node.
	 */
	private AvlNode rotateLeft(AvlNode n) {
		AvlNode node = n.right;
		node.parent = n.parent;
		n.right = node.left;
		if (n.right!=null) {
			n.right.parent=n;
		}
		node.left = n;
		n.parent = node;
		if (node.parent!=null) {
			if (node.parent.right==n) {
				node.parent.right = node;
			} 
			else if (node.parent.left==n) {
				node.parent.left = node;
			}
		}
		//finished rotating, now update balance
		setBalance(n);
		setBalance(node);
		return node;
	}

	/*
	 * Right rotation using the given node.
	 */
	private AvlNode rotateRight(AvlNode n) {
		AvlNode node = n.left;
		node.parent = n.parent;
		n.left = node.right;
		if (n.left!=null) {
			n.left.parent=n;
		}
		node.right = n;
		n.parent = node;
		if (node.parent!=null) {
			if (node.parent.right==n) {
				node.parent.right = node;
			} 
			else if (node.parent.left==n) {
				node.parent.left = node;
			}
		}
		//finished rotating, now update balance
		setBalance(n);
		setBalance(node);
		return node;
	}
	
	/*
	 * Left double rotation using the given node.
	 */
	private AvlNode doubleRotateLeftRight(AvlNode n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	/*
	 * Right double rotation using the given node.
	 */
	private AvlNode doubleRotateRightLeft(AvlNode n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	/*
	 * Returns the successor of a given node in the tree (search recursively).
	 */
	private AvlNode successor(AvlNode predecessor) {
		if (predecessor.right!=null) {
			AvlNode right = predecessor.right;
			while (right.left!=null) {
				right = right.left;
			}
			return right;
		} 
		else {
			AvlNode parent = predecessor.parent;
			while (parent!=null && predecessor==parent.right) {
				predecessor = parent;
				parent = predecessor.parent;
			}
			return parent;
		}
	}

	/*
	 * Calculate the height of a node.
	 */
	private int height(AvlNode cur) {
		if (cur==null) {
			return -1;
		}
		int leftHeight = 1+height(cur.left);
		int rightHeight = 1+height(cur.right);
		return Math.max(leftHeight,rightHeight);
	}
	/*
	 * Calculate the depth of a node (the number of nodes from the given node (not including)
	 * to the root).
	 */
	private int depth(AvlNode cur) {
		if (cur==null) {
			return -1;
		}
		int depth = 1+depth(cur.parent);
		return depth;
	}

	/*
	 * set the balance for the given cur
	 */
	private void setBalance(AvlNode cur) {
		cur.balance = height(cur.left)-height(cur.right);
	}
}