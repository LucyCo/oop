package clids.ex5.crosswords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import clids.ex5.crosswords.CrosswordStructure.SlotType;
import clids.ex5.search.SearchBoard;

public class MyCrossword implements Crossword {
	private int qualityBound;
	private int boardQuality;
	private int numOfMoves;
	private CrosswordStructure struct;
	/**
	 * current dictionary
	 */
	CrosswordDictionary dict;
	/**
	 * current word terms
	 */
	String[] terms;
	/**
	 * available positions for a given word
	 */
	CrosswordPosition[][] positions; 
	/**
	 * all entries
	 */
	Map<String,CrosswordEntry> entry;
	
	/**
	 * empty constructor for MyCrossword object
	 */
	public MyCrossword() {
		boardQuality = 0;
		numOfMoves = 0;
		entry = new HashMap<String, CrosswordEntry>();
	}
	
	/**
	 * copy constructor
	 * @param crossword the crossword to copy
	 */
	public MyCrossword(MyCrossword crossword) {
		entry = new HashMap<String, CrosswordEntry>();
		struct = crossword.struct;
		dict = crossword.dict;
		entry.putAll(crossword.entry);
		boardQuality = crossword.boardQuality;
		numOfMoves = crossword.numOfMoves;
		terms = crossword.terms;
		positions = crossword.positions;
		qualityBound = crossword.qualityBound;
	}
	//========= implementation for searchBoard class ==========
	//constructor
	/**
	 * Creates a stand-alone copy of the current board.
	 * The returned copy should not be affected by subsequent doMove/undoMove
	 * operations on the current board.
	 * @return a copy of given board.
	 */	
	@Override
	public SearchBoard<CrosswordEntry> getCopy() {
		return new MyCrossword(this);
	} 
	
	/**
	 * Returns true if a node object represents a one of the best 
	 * possible solutions of the problem, false otherwise.
	 * 
	 * @return True/False
	 */
	@Override
	public boolean isBestSolution() {
		if (boardQuality==dict.getAllTermLength()) {
			return true;
		}
		return false;
	}
	/**
	 * Creates and returns an iterator on the list of nodes reachable by a
	 * single edge from the current node (in case of crossword - a list of
	 * crosswords with one more word in the grid).
	 * 
	 * @return Iterator object
	 */
	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		//we went over all the words, return an empty iterator
		if(terms.length<=numOfMoves) {
			ArrayList<CrosswordEntry> array = new ArrayList<CrosswordEntry>();
			return array.iterator();		
		}
		return new Moves(numOfMoves, this);
	}
	
	/**
	 * This function allows evaluation of quality of solutions 
	 * (higher values mean better solutions).
	 * 
	 * @return The quality value of this board.
	 */
	@Override
	public int getQuality() {
		return boardQuality;
	}

	/**
	 * This function allows estimation of potential 
	 * upper bound on quality of solutions available through zero or 
	 * more doMove() operations from the current board.
	 * 
	 * The returned value have to be always greater or equal to
	 * the best possible quality obtained through doMove operations.
	 * Hence, it is also assumed that doMove operation should never increase 
	 * the upper bound value.
	 * 
	 * @return The upper bound on quality of this board.
	 */
	@Override
	public int getQualityBound() {
		return qualityBound;
	}

	/**
	 * Performs a move on the board potentially (reversibly) changing 
	 * the current board object.
	 * @param move
	 */
	@Override
	public void doMove(CrosswordEntry move) {
		numOfMoves++;
		if(move==null){
			qualityBound = (qualityBound-terms[numOfMoves-1].length());
			return;
		}
		entry.put(move.getTerm(), move);
		boardQuality = (boardQuality+move.getLength());
	}
	
	/**
	 * Restores the object to state before the last move.
	 * It is assumed that sequence of undoMove operations always
	 * reflect in the correct order the sequence of doMove operations.
	 * Hence undoMove is always supplied with the most recent un-undoed move.
	 * (You don't have to check this assumption)
	 * @param move
	 */	
	@Override
	public void undoMove(CrosswordEntry move) {
		numOfMoves--;
		if(move==null) {
			qualityBound = (qualityBound + terms[numOfMoves].length());
			return;
		}
		entry.remove(move.getTerm());
		boardQuality = (boardQuality-move.getLength());
	}

	/**
	 * Initializes all structures associated with crossword dictionary 
	 * Assumes valid and non-NULL dictionary object
	 * 
	 * @param dictionary - the dictionary object
	 * 					  (generated according to a text file)
	 */
	@Override
	public void attachDictionary(CrosswordDictionary dictionary) {
		dict = dictionary;
		qualityBound=dictionary.getAllTermLength();
		terms = dict.getTerms().toArray(new String[dict.getTerms().size()]);
		Arrays.sort(terms, new StringLength()); 
		if(struct!=null) {
			positions();
		}
	}

	/**
	 * Initializes all data structures associated with crossword structure. 
	 * Assumes valid and non-NULL shape object
	 * 
	 * @param shape - the structure object (generated according to a text file)
	 */
	@Override
	public void attachStructure(CrosswordStructure structure) {
		struct = structure;
		if(dict!=null) {
			positions();
		}
	}

	/**
	 * Retrieves list of filled crossword entries associated with this 
	 * Crossword.The set of filled entries should satisfy both of 
	 * exercise requirements. 
	 * 
	 * There is no requirement on the order of the returned collection
	 * of entries.
	 * 
	 * @return Collection of filled entries.
	 */
	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {
		return entry.values();
	}
	
	//=========== helper method =============
	/*
	 * this method updates the positions array for a given word s.t. all available positions are 
	 * listed in this array, measuring an available positions for each of the words according to the
	 * instructions given on ex5 description
	 */
	private void positions() {
		positions = new CrosswordPosition[dict.getMaxTermLength()][];
		for(int size = 0;size<dict.getMaxTermLength();size++) {
			positions[size] = new CrosswordPosition[struct.getHeight()
			                                        *(struct.getWidth()*2)];
			int index = 0;
			for(int x = 0;x<struct.getWidth();x++) {
				for(int y = 0;y<struct.getHeight();y++) {
					boolean isMoveLegal = true;
					for(int k = 0;k<=size;k++)
						if(struct.getSlotTypeByPos(x+k,y)==SlotType.FRAME_SLOT) {
							isMoveLegal = false;
						}
					if(isMoveLegal) {
						positions[size][index++] = new MyCrosswordPosition(x, y, false);
					}
					isMoveLegal = true;
					for(int k=0;k<=size;k++) {
						if(struct.getSlotTypeByPos(x,y+k)==SlotType.FRAME_SLOT) {
							isMoveLegal=false;
						}
					}
					if(isMoveLegal) {
						positions[size][index++]=new MyCrosswordPosition(x, y, true);
					}
				}
			}
		}
	}
}
