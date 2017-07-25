package clids.ex3.data_structures;

class AvlNode {
	
	protected AvlNode right;
	protected AvlNode left;
	protected AvlNode parent;
	protected int value;
	protected int balance;
	protected AvlNode(int value) {
		left = right = parent = null;
		this.value = value;
		balance = 0;
	}
}
