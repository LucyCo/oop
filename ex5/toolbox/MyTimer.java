package clids.ex5.toolbox;

import java.util.Timer;
import java.util.TimerTask;

import clids.ex5.search.MyDepthFirstSearch.TimeOut;

/**
 * this class can stop the running of search when it reaches the amount of time
 * given by the user
 */
public class MyTimer extends TimerTask {
	@SuppressWarnings("rawtypes")
	private TimeOut stop;
	private Timer timer;
	
	/**
	 * constructor
	 * @param stop timeOut object that was implemented in myfirstdepthsearch
	 * @param timeOut the given time to stop the search
	 */
	@SuppressWarnings("rawtypes")
	public MyTimer(TimeOut stop, long timeOut) {
		this.stop = stop;
		timer = new Timer();
		timer.schedule(this, timeOut);
	}
	/**
	 * stop the timer
	 */
	public void stop() {
		timer.cancel();
	}
	/**
	 * set timeOut to true and stop the timer
	 */
	@Override
	public void run() {
		stop.timeOut();stop();
	}
}