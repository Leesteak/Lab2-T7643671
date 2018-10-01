import java.util.Arrays;
/**
* Data structure used to hold data about individual test runs
*
*/

public class TestAction {
	private TStack<Float> timesStack;
	private float median;
	private float total;
	private float best;
	private float worst;
	private byte actionType;
	
	
	/**
	* Constructor for the class
	*/
	
	public TestAction(byte actionType){
		this.timesStack = new TStack<Float>();
		this.worst = 0;
		this.best = -1;
		this.actionType = actionType;
	}
	
	/**
	* Adds time into the stack and collects some stats
	*
	* @param time the time to add to the stack
	*/
	
	public void addTime(long time){
		timesStack.push((float) time);
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
	
	public float getLastTime() {
		return timesStack.pop();
	}
	
	/**
	* Return the total time for the run
	*/
	public float getTotal() {
		return total;
	}
	
	/**
	* Return the average time of the run
	*/
	
	public float getAverage() {
		return total/(float) getTestSize();
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
	
	public float getWorst() {
		return worst;
	}
	
	/**
	* Return the best sample time in the run
	*/
	
	public float getBest() {
		return best;
	}
	
	/**
	* Return the name of the test
	*/
	
	public byte getTestType() {
		return actionType;
	}
	
	/**
	* Return the median value of the run and clears the stack (avoid those pesky OutOfMemory errors)
	*/
	
	public float getMedian() {
		if (median != -1) return median; // Save the median value if no more values have been added
		int size = timesStack.size();
		Float[] values = new Float[size];
		
		// Load all values into the 'values' array
		for (int i = 0; i < size; i++) {
			values[i] = timesStack.pop();
		}
		
		Arrays.sort(values);
		
		if (size % 2 == 0) median = ((float) values[size/2] + (float) values[size/2-1]) / 2;
		else median = (float) values[size/2];

		
		return median;
	}
}