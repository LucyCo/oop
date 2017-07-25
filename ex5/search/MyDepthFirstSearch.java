package clids.ex5.search;
import java.util.*;
import clids.ex5.toolbox.MyTimer;


/**
 * this class implements DepthFirstSearch and extends SearchBoard and BoardMove
 */
public class MyDepthFirstSearch<B extends SearchBoard<M>, M extends BoardMove>
		implements DepthFirstSearch<B, M> {
	private boolean _timeOut;
	
	/**
	 * this class contains the method TimeOut, upon calling this method the isTimeOut
	 * variable will change to true and the search will stop
	 */
	public class TimeOut{
		public void timeOut() {
			_timeOut = true;
		}
	}

	@Override
	public B performSearch(B startBoard, int maxDepth, long timeOut) {
		_timeOut = false;
		MyTimer myTimer = new MyTimer(new TimeOut(), timeOut);
		B awesomeBoard = searchHelper(startBoard, maxDepth, timeOut);
		//stop the timer if the best crossboard is found before timeout happened
		myTimer.stop();
		return awesomeBoard;
	}

	/*
	 * this method runs all over the possible boards in the given time and returns the
	 * best possible solution.
	 * It does the search recursively with graph search depth first while the depth
	 * is the given maxDepth param
	 */
	@SuppressWarnings("unchecked")
	private B searchHelper(B startBoard, int maxDepth, long timeOut) {
		if (startBoard.isBestSolution() || _timeOut) {
			if (maxDepth==0) {
				return startBoard;
			}
		}
		int quality = startBoard.getQuality();
		Iterator<M> moves = startBoard.getMovesIterator();
		//begin with start board being the best option
		SearchBoard<M> currentBest = startBoard;
		while(moves.hasNext()&&(_timeOut == false)) {
			M move = moves.next();
			startBoard.doMove(move);
			if(quality >= startBoard.getQualityBound()){
				//we can stop. this board is not the best solution.
				startBoard.undoMove(move);
				continue;
			}
			//call recursively for the node one depth higher until depth=0 and return
			//the best solution
			currentBest = searchHelper(startBoard, maxDepth-1, timeOut);
			if(currentBest.isBestSolution()) {
				return (B)currentBest;
			}
			if(currentBest.getQuality() > quality) {
				//update current best solution
				currentBest = currentBest.getCopy();
				//update quality
				quality = currentBest.getQuality();
			}
			startBoard.undoMove(move);
		}
		return (B)currentBest;
	}
}