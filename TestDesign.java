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
	static final byte DISTANCE = 0;
	static final byte ROTATE = 1;
	static final byte CARTESIAN = 2;
	static final byte POLAR = 3;
	
	/**
	* The name of the Design that was tested
	*/
	
	private String designName;
	
	/**
	* Arrays to hold all tests and statistics
	*/

	private float[] sampleBest = new float[4];
	private float[] sampleWorst = new float[4];
	private float[] totalBest = new float[4];
	private float[] totalWorst = new float[4];
	private float[] total = new float[4];
	private float[] sampleAverages = new float[4];
	private float[] medianSum = new float[4];
	private int[] tests = new int[4];
	
	
	/**
	* Constructor method for the class. Accepts as input an integer value of the design to name it properly
	* 
	* @param design the number of the design (1, 2, or 3) 
	*/
	
	public TestDesign(int design) {
		if (design > 3) throw new IndexOutOfBoundsException();

		if (design < 3) {
			designName = "Design " + (design + 1);
		} else {
			designName = "Design 6";
		}
		for (int i = 0; i < 4; i++) {
			sampleBest[i] = -1;
			sampleWorst[i] = 0;
			totalBest[i] = -1;
			totalWorst[i] = 0;
			total[i] = 0;
			tests[i] = 0;
		}
	}
	
	/**
	* Extracts statistics and adds the test to the appropriate stack
	*/
	
	public void addTest(TestAction test) {
		float best = test.getBest();
		float worst = test.getWorst();
		float total = test.getTotal();
		float average = (float) test.getAverage();
		// getMedian clears the internal stack for the TestAction so we don't run out of memory
		double median = test.getMedian();
		byte type = test.getTestType();

		if (sampleBest[type] == -1 || sampleBest[type] > best) sampleBest[type] = best;
		if (sampleWorst[type] < worst) sampleWorst[type] = worst;
		if (totalBest[type] == -1 || totalBest[type] > total) totalBest[type] = total;
		if (totalWorst[type] < total) totalWorst[type] = total;
		
		this.total[type] += total;
		this.medianSum[type] += median;
		this.sampleAverages[type] += average;
		this.tests[type]++;
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
	* @param test the type of the test
	*/
	
	public float getAverage(int test) {
		return total[test]/tests[test];
	}
	
	/**
	* Returns the total runtime of a specific test
	*
	* @param test the type of the test
	*/
	
	public float getTotal(int test) {
		return total[test];
	}
	
	/**
	* Returns the average of all the medians of a specific test
	*
	* @param test the type of the test
	*/
	
	public float getMedianAverage(int test) {
		return medianSum[test]/tests[test];
	}
	
	/**
	* Returns the fastest time for a single test element in a run
	*
	* @param test the type of the test
	*/
	
	public float getSampleBest(int test) {
		return sampleBest[test];
	}
	
	/**
	* Return the slowest time for a single test element in a run
	*
	* @param test the type of the test
	*/
	
	public float getSampleWorst(int test) {
		return sampleWorst[test];
	}
	
	/**
	* Return the fastest overall time for a run
	*
	* @param test the type of the test
	*/
	
	public float getTotalBest(int test) {
		return totalBest[test];
	}
	
	/**
	* Return the slowest overall time for a run
	*
	* @param test the type of the test
	*/
	
	public float getTotalWorst(int test) {
		return totalWorst[test];
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
	* @param test the type of the test
	*/
	
	public float getSampleAverage(int test) {
		return sampleAverages[test]/tests[test];
	}
}