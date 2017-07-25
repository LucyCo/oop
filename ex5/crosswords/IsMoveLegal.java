package clids.ex5.crosswords;

/**
 * this class implements methods for testing the legality of moves in crosswords
 * moves are possible places to put words
 */
public class IsMoveLegal {

	/**
	 * return true if the current placing of a term on the board is legal
	 * @param position the position we want to place on
	 * @param term the current term that we want to place on the board
	 * @param currentTerms the words that are currently on the crossboard
	 * @return true if move is legal
	 */
	public static boolean checkIfLegal(CrosswordPosition position, String term, Iterable<CrosswordEntry> currentTerms) {
		if (position.isVertical()) {
			return vertical(term, position.getX(), position.getY(), currentTerms);
		}
		return horizonal(term, position.getX(), position.getY(), currentTerms);
	}

	private static boolean vertical(String term, int posX, int posY, Iterable<CrosswordEntry> words) {
		for(CrosswordEntry entry:words) {
			CrosswordPosition pos = entry.getPosition();
			if(pos.isVertical()){
				if(pos.getX()==posX && !trySameLine(entry.getTerm(), term, pos.getY(), posY)) {
					return false;
				}
			}
			else {
				MyCrosswordPosition posVert = new MyCrosswordPosition(posX, posY, true);
				if(!tryAcross(entry.getTerm(), term, pos, posVert)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean horizonal(String term, int posX, int posY,
			Iterable<CrosswordEntry> words) {
		for(CrosswordEntry entry:words) {
			CrosswordPosition pos = entry.getPosition();
			if(!pos.isVertical()){
				if(!trySameLine(entry.getTerm(), term, pos.getX(), posX)&&pos.getY()==posY) {
					return false;
				}
			}
			else {
				MyCrosswordPosition posHorizonal = new MyCrosswordPosition(posX, posY, false);
				if(!tryAcross(term, entry.getTerm(), posHorizonal, pos)) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * check legality for cross moves for given two terms
	 */
	private static boolean tryAcross(String horizonal, String vertical, CrosswordPosition horizon,
			CrosswordPosition vertic) {
		int epsilon = (vertic.getX()-horizon.getX());
		int delta = horizon.getY()-vertic.getY();
		if(epsilon<0||delta<0||epsilon>=horizonal.length()||delta>=vertical.length()) {
			return true;
		}
		return (horizonal.charAt(epsilon)==vertical.charAt(delta));
	}

	/*
	 * check legality for placing two terms in the same line
	 */
	private static boolean trySameLine(String term, String otherTerm, int termPos, int otherPos) {
		int epsilon = (termPos-otherPos);
		if(epsilon==0) {
			return false;
		}
		if(epsilon>0) {
			if(Math.abs(epsilon)>otherTerm.length()) {
				return true;
			}
			else {
				return otherTerm.regionMatches(Math.abs(epsilon), term, 0, 
						Math.min(term.length(), otherTerm.length()-epsilon));
			}
		}
		return false;
	}
}