package clids.ex5.crosswords;

/**
 * Specifies a basic position of an entry in a crossword.
 */
public class MyCrosswordPosition implements CrosswordPosition {
	private boolean _isVertical;
	private int _x;
	private int _y;

	/**
	 * constructor for a MyCrosswordPosition object
	 * @param x the x coordinate of the position
	 * @param y the y coordinate of the position
	 * @param isVertical if true then vertical, else, horizontal
	 */
	public MyCrosswordPosition(int x, int y, boolean isVertical) {
		_isVertical=isVertical;
		_x=x;
		_y=y;
	}

	/**
	 * @return The x coordinate.
	 */
	@Override
	public int getX() {
		return _x;
	}

	/**
	 * 
	 * @return The Y coordinate.
	 */
	@Override
	public int getY() {
		return _y;
	}

	/**
	 * @return True iff position is a vertical position.
	 */
	@Override
	public boolean isVertical() {
		return _isVertical;
	}
}
