package clids.ex5.crosswords;

import java.util.Iterator;

/**
 * this iterator runs over legal available moves for pacing each word. 
 * this class will be called by the method 
 * MyCrossword.getMovesIterator().
 */
public class Moves implements Iterator<CrosswordEntry> {
	private MyCrossword board;
	private int indexOfMove;
	private int indexOfTerm;
	private int sizeOfTerm;

	/**
	 * a constructor for a new Moves object 
	 * @param parent the board
	 * @param term current word to place on the board
	 */
	public Moves(int word, MyCrossword crossboard) {
		indexOfMove = -1;
		board = crossboard;
		indexOfTerm = word;
		sizeOfTerm = (crossboard.terms[word].length()-1);
	}

	public void remove() {}
	
	/**
	 * returns true if next available move exists
	 */
	public boolean hasNext() {
		if(indexOfMove == -1){
			return true;
		}
		boolean isNull = board.positions[sizeOfTerm][indexOfMove]!=null;
		return isNull;
	}
	/**
	 * returns the next move, this method will be called iff hasNext returns true.
	 */
	public CrosswordEntry next() {
		++indexOfMove;
		while(board.positions[sizeOfTerm][indexOfMove]!=null&& !IsMoveLegal.checkIfLegal(board.positions
				[sizeOfTerm][indexOfMove],board.terms[indexOfTerm],board.entry.values())){
			++indexOfMove;	
		}
		if(board.positions[sizeOfTerm][indexOfMove]==null) {
			return null;
		}
		return new MyCrosswordEntry(board.positions[sizeOfTerm][indexOfMove], board.terms[indexOfTerm], 
				board.dict.getTermDefinition(board.terms[indexOfTerm]));
	}
}