package clids.ex5.crosswords;

/**
 * A crossword entry is a word at a given position in a crossword.
 */
public class MyCrosswordEntry implements CrosswordEntry {
	private CrosswordPosition position;
	private String definition;
	private String term;

	/**
	 * constructor for CrossboardEntry object
	 * 
	 * @param position current position of the term on the board
	 * @param term the term of the entry
	 * @param definition definition of the term
	 */
	public MyCrosswordEntry(CrosswordPosition position, String term, String definition) {
		this.term = term;
		this.position = position;
		this.definition = definition;
	}

	/**
	 * Returns the X/Y/Vertical position of an entry
	 * @return the position
	 */
	@Override
	public CrosswordPosition getPosition() {
		return position;
	}

	/**
	 * Returns the corresponding definition of the entry.
	 * 
	 * @return the corresponding dictionary definition.
	 */
	@Override
	public String getDefinition() {
		return definition;
	}

	/**
	 * Returns the corresponding word of the entry.
	 * 
	 * @return the corresponding term.
	 */
	@Override
	public String getTerm() {
		return term;
	}

	/**
	 * Retrieves length of the entry (redundant convenience, may be calculated
	 * through getTerm()).
	 * 
	 * @return number of letters of the term.
	 */
	@Override
	public int getLength() {
		return term.length();
	}

}