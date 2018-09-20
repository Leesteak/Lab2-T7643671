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
	
	private TStack<TestAction>[] results = new TStack<TestAction>[4]
	private long[] sampleBest = new long[4];
	private long[] sampleWorst = new long[4];
	private long[] totalBest = new long[4];
	private long[] totalWorst = new long[4];
	private long[] total = new long[4];
	private long[] sampleAverages = new long[4];
	private long[] medianSum = new long [4];
	
	
	/**
	* Constructor method for the class. Accepts as input an integer value of the design to name it properly
	* 
	* @param design the number of the design (1, 2, or 3) 
	*/
	
	public TestDesign(int design) {
		if (design > 3) throw IndexOutOfBoundsException();
		
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
		}
	}
	
	/**
	* Converts the name of the test to one of the constants to make navigating the arrays easier
	*
	* @param testName the name of the test
	*/
	private int convertNameToType(String testName) {
		if (testName == "Calculate Distance") return DISTANCE;
		else if (testName == "Rotate Point") return ROTATE;
		else if (testName == "Generate Coordinate Cartesian") return CARTESIAN;
		else if (testName == "Generate Coordinate Polar") return POLAR;
	}
	
	/**
	* Extracts statistics and adds the test to the appropriate stack
	*/
	
	public void addTest(TestAction test) {
		long best = test.getBest();
		long worst = test.getWorst();
		long total = test.getTotal();
		long median = test.getMedian();
		long average = test.getAverage();
		int type = convertNameToType(test.getTestName());
		
		results[type].push(test);
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
		
		return total[test]/results[test].getSize();
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
		
		return medianSum[test]/results[test].getSize();
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
		
		return sampleTotalBest[test];
	}
	
	/**
	* Return the slowest overall time for a run
	*
	* @param testName the name of the test
	*/
	
	public long getTotalWorst(String testName) {
		int test = convertNameToType(testName);
		
		return sampleTotalWorst[test];
	}
	
	/**
	* Return the last added test result for a given test and removes it from the stack
	*
	* @param testName the name of the test
	*/
	
	public long popTest(String testName) {
		int test = convertNameToType(testName);
		
		return results[test].pop();
	}
	
	/**
	* Return the name of the design
	*
	* @param testName the name of the test
	*/
	
	public String getDesignName() {
		return designName;
	}
	
	/**
	* Return the average of all tests for a given test
	*
	* @param testName the name of the test
	*/
	
	public long getSampleAverage(String testName) {
		int test = convertNameToType(testName);
		
		return sampleAverages[test]/results[test].getSize();
	}
}