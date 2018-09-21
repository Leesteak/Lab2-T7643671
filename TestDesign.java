/**
* Data structure made to extract, organize and categorize data
* from TestAction objects 
*
* @author Natalia Maximo <natalia.maximo@uottawa.ca>
*/

public class TestDesign {
	
	/**
	* Constants for test array position
	*/
	static final int DISTANCE = 0;
	static final int ROTATE = 1;
	static final int CARTESIAN = 2;
	static final int POLAR = 3;
	
	/**
	* The name of the Design that was tested
	*/
	
	private String designName;
	
	/**
	* Arrays to hold all tests and statistics
	*/
	
	private Object[] results = new Object[4];
	private long[] sampleBest = new long[4];
	private long[] sampleWorst = new long[4];
	private long[] totalBest = new long[4];
	private long[] totalWorst = new long[4];
	private long[] total = new long[4];
	private double[] sampleAverages = new double[4];
	private long[] medianSum = new long [4];
	
	
	/**
	* Constructor method for the class. Accepts as input an integer value of the design to name it properly
	* 
	* @param design the number of the design (1, 2, or 3) 
	*/
	
	public TestDesign(int design) {
		if (design > 3) throw new IndexOutOfBoundsException();
		
		if (design < 3) {
			designName = "Design " + (design+1);
		}
		else {
			designName = "Design 6";
		}
		for (int i = 0; i < 4; i++) {
			sampleBest[i] = -1;
			sampleWorst[i] = 0;
			totalBest[i] = -1;
			totalWorst[i] = 0;
			total[i] = 0;
			results[i] = new TStack<TestAction>();
		}
	}
	
	/**
	* Converts the name of the test to one of the constants to make navigating the arrays easier
	*
	* @param testName the name of the test
	*/
	private int convertNameToType(String testName) {
		if (testName.equals("Calculate Distance")) return DISTANCE;
		else if (testName.equals("Rotate Point")) return ROTATE;
		else if (testName.equals("Generate Coordinate Cartesian")) return CARTESIAN;
		else if (testName.equals("Generate Coordinate Polar")) return POLAR;
		else return -1;
	}
	
	/**
	* Extracts statistics and adds the test to the appropriate stack
	*/
	
	public void addTest(TestAction test) {
		long best = test.getBest();
		long worst = test.getWorst();
		long total = test.getTotal();
		double average = test.getAverage();
		// getMedian clears the internal stack for the TestAction so we don't run out of memory
		double median = test.getMedian();
		int type = convertNameToType(test.getTestName());
		
		((TStack<TestAction>) results[type]).push(test);
		if (sampleBest[type] == -1 || sampleBest[type] > best) sampleBest[type] = best;
		if (sampleWorst[type] < worst) sampleWorst[type] = worst;
		if (totalBest[type] == -1 || totalBest[type] > total) totalBest[type] = total;
		if (totalWorst[type] < total) totalWorst[type] = total;
		
		this.total[type] += total;
		this.medianSum[type] += median;
		this.sampleAverages[type] += average;
	}
	
	/**
	*
	*=========================================================================
	* GETTER FUNCTIONS
	*=========================================================================
	*
	*/
	
	/**
	* Returns the average run time of a specific test
	*
	* @param testName the name of the test
	*/
	
	public long getAverage(String testName) {
		int test = convertNameToType(testName);
		
		return total[test]/((TStack<TestAction>)results[test]).size();
	}
	
	/**
	* Returns the total runtime of a specific test
	*
	* @param testName the name of the test
	*/
	
	public long getTotal(String testName) {
		int test = convertNameToType(testName);
		
		return total[test];
	}
	
	/**
	* Returns the average of all the medians of a specific test
	*
	* @param testName the name of the test
	*/
	
	public long getMedianAverage(String testName) {
		int test = convertNameToType(testName);
		
		return medianSum[test]/((TStack<TestAction>)results[test]).size();
	}
	
	/**
	* Returns the fastest time for a single test element in a run
	*
	* @param testName the name of the test
	*/
	
	public long getSampleBest(String testName) {
		int test = convertNameToType(testName);
		
		return sampleBest[test];
	}
	
	/**
	* Return the slowest time for a single test element in a run
	*
	* @param testName the name of the test
	*/
	
	public long getSampleWorst(String testName) {
		int test = convertNameToType(testName);
		
		return sampleWorst[test];
	}
	
	/**
	* Return the fastest overall time for a run
	*
	* @param testName the name of the test
	*/
	
	public long getTotalBest(String testName) {
		int test = convertNameToType(testName);
		
		return totalBest[test];
	}
	
	/**
	* Return the slowest overall time for a run
	*
	* @param testName the name of the test
	*/
	
	public long getTotalWorst(String testName) {
		int test = convertNameToType(testName);
		
		return totalWorst[test];
	}
	
	/**
	* Return the last added test result for a given test and removes it from the stack
	*
	* @param testName the name of the test
	*/
	
	public TestAction popTest(String testName) {
		int test = convertNameToType(testName);
		
		return ((TStack<TestAction>)results[test]).pop();
	}
	
	/**
	* Return the name of the design
	*/
	
	public String getDesignName() {
		return designName;
	}
	
	/**
	* Return the average of all tests for a given test
	*
	* @param testName the name of the test
	*/
	
	public double getSampleAverage(String testName) {
		int test = convertNameToType(testName);
		
		return sampleAverages[test]/((TStack<TestAction>) results[test]).size();
	}
}