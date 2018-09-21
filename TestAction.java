import java.util.Arrays;
/**
* Data structure used to hold data about individual test runs
*
*/

public class TestAction {
	private TStack<Long> timesStack;
	private double median;
	private long total;
	private long best;
	private long worst;
	private String actionName;
	
	
	/**
	* Constructor for the class
	*/
	
	public TestAction(String actionName){
		this.timesStack = new TStack<Long>();
		this.worst = 0;
		this.best = -1;
		this.actionName = actionName;
	}
	
	/**
	* Adds time into the stack and collects some stats
	*
	* @param time the time to add to the stack
	*/
	
	public void addTime(long time){
		timesStack.push(time);
		total += time;
		if (best == -1 || best > time) best = time;
		if (worst < time) worst = time;
		median = -1;
	}
	
	/**
	*
	*=========================================================================
	* GETTER FUNCTIONS
	*=========================================================================
	*
	*/
	
	/**
	* Pops the last time from the stack
	*/
	
	public long getLastTime() {
		return timesStack.pop();
	}
	
	/**
	* Return the total time for the run
	*/
	public long getTotal() {
		return total;
	}
	
	/**
	* Return the average time of the run
	*/
	
	public double getAverage() {
		return (double) total/(double) getTestSize();
	}
	
	/**
	* Return the number of samples in the run
	*/ 
	
	public int getTestSize() {
		return timesStack.size();
	}
	
	/**
	* Return the worst sample time in the run
	*/
	
	public long getWorst() {
		return worst;
	}
	
	/**
	* Return the best sample time in the run
	*/
	
	public long getBest() {
		return best;
	}
	
	/**
	* Return the name of the test
	*/
	
	public String getTestName() {
		return actionName;
	}
	
	/**
	* Return the median value of the run
	*/
	
	public double getMedian() {
		if (median != -1) return median; // Save the median value if no more values have been added
		int size = timesStack.size();
		long[] values = new long[size];
		
		// Load all values into the 'values' array
		for (int i = 0; i < size; i++) {
			values[i] = timesStack.pop();
		}
		
		Arrays.sort(values);
		
		if (size % 2 == 0) median = ((double) values[size/2] + (double) values[size/2-1]) / 2;
		else median = (double) values[size/2];
		
		
		// Push values back onto the queue in case more values get added later, to re-calculate the median if need be
		for (int i = 0; i < size; i++) {
			timesStack.push(values[i]);
		}
		
		return median;
	}
}