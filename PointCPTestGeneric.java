import java.util.Scanner;


/**
 * Test class for all designs
 */
public class PointCPTestGeneric {


	// Testing Constants
    static final int RUNS = 100;
    static final int TEST_SIZE = 10000;
    static final int DESIGN = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // User input scanner
        TStack<TestDesign> results; // Used a stack in case of full test
        int runs;
        int design;
        int testSize;
        
        // Design selection
        System.out.println("Which design do you want to test?");
        System.out.println("1: Design 2");
        System.out.println("2: Design 3");
        System.out.println("3: Design 6");
        System.out.println("4: Full test (default)");
        System.out.println("0: Cancel\n");
        System.out.print(":");
        
        try {
        	design = scanner.nextInt();
        	if (design > 4 || design < 0) throw new Exception(); // Only 4 test options and an exit condition
        }
        catch (Exception e) {
        	System.out.printf("Error occurred, using default value %d... \n", DESIGN);
        	design = DESIGN;
        }
        
        if (design == 0) return;
        
        System.out.println("Running test on " + (design = 4) ? ("all designs") : ((design < 3) ? ("Design " + (design+1)) : "Design 6"));
        
        
        // Test size
        System.out.print("How many tests to run: ");
        
        try {
            runs = scanner.nextInt();
            if (runs < 1) throw new Exception();
        }
        catch (Exception e) {
            System.out.printf("Error occurred, using default value %d... \n", RUNS);
            runs = RUNS;
        }
        
        System.out.print("What should be the test size?")
        
        try {
        	testSize = scanner.nextInt();
        }
        catch (Exception e) {
        	System.out.printf("Error occurred, using default value %d... \n", TEST_SIZE);
        	testSize = TEST_SIZE;
        }
        
        if (testSize < TEST_SIZE) {
        	System.out.printf("Test size too small, using default value %d... \n", TEST_SIZE);
 			testSize = TEST_SIZE;       	
        }
        
        System.out.printf("Starting %d runs with a test size of %d\n", runs, testSize);
        
    	if (design < 4) {
    		results.push(testDesign(3, runs, size));
    		results.push(testDesign(2, runs, size));
    		results.push(testDesign(1, runs, size));
    	}
    	else {
    		results.push(testDesign(design, runs, size));
    	}
    	
    	while (results.peek() != null) { // See all results that have been collected
    		processTest(results.pop());
    	}
    }
    
    private static void processTest(TestDesign design) {
    	String[] tests = {"Calculate Distance", "Rotate Point", "Generate Coordinate Cartesian", "Generate Coordinate Polar"}
    	System.out.println("Data for " + design.getDesignName() + "...");
    	
    	// Spit out all test statistics
    	for (int i = 0; i < tests.length; i++){
    		String test = tests[i]
    		System.out.printf("\tTest name: %s\n", test);
    		// Overall stats
    		System.out.println("\t\tTotal runtime: " + design.getTotal(test));
    		System.out.println("\t\tAverage test time: " + design.getAverage(test));
    		System.out.println("\t\tBest test time: " + design.getTotalBest(test));
    		System.out.println("\t\tWorst test time: " + design.getTotalWorst(test));
    		System.out.println();
    		// Sample Stats
    		System.out.println("\t\tBest sample time: " + design.getSampleBest(test));
    		System.out.println("\t\tWorst sample time: " + design.getSampleWorst(test));
    		System.out.println("\t\tAverage sample time: " + design.getSampleAverage(test));
    		System.out.println();
    		System.out.println("\t\tAverage of median times: " + design.getMedianAverage(test));
    	}
    }
    
    private static TestDesign testDesign(int design, int runs, int size) {
    	TestDesign results = new TestDesign(design);
    	long start, end;
    	
    	
    	for (int i = 0; i < runs; i++) {
    		
    		// Initialize the appropriate design object
    		if (design == 1) Design2.PointCP[] points = new Design2.PointCP[size];
    		else if (design == 2) Design3.PointCP[] points = new Design3.PointCP[size];
    		else Design6.PointCP[] points = new Design6.PointCP[size];
    		
    		// Generate appropriate point instances for each design
    		for (int j = 0; j < size; j++) {
    			if (design == 1) { // Design 2
    				points[i] = new Design2.PointCP('P', generateCoord('P'), generateCoord('P'));
    			}
    			else if (design == 2) { // Design 3
    				points[i] = new Design3.PointCP('C', generateCoord('C'), generateCoord('C'));
    			}
    			else { // Design 6
    				if (i < (size/2)) {
    					points[i] = new Design6.PointPolar(generateCoord('P'), generateCoord('P'));
    				}
    				else {
    					points[i] = new Design6.PointCartesian(generateCoord('C'), generateCoord('C'));
    				}
    			}
    		}
    		
    		// Run all tests and add them to the TestDesign
    		for (int j = 0; j < size; j++){
    			results.addTest(testDistance(design, points));
    			results.addTest(testRotate(design, points));
    			results.addTest(testCoordinates(design, points, 'C'));
    			results.addTest(testCoordinates(design, points, 'P'));
    		}
    	}
    	
    	return results;
    }
    
    private static TestAction testDistance(int design, Object[] points) { // Using generic Object class before casting
    	if (design == 1) {
    		Design2.PointCP[] testing = (Design2.PointCP[]) points;
    		Design2.PointCP testPoint = new Design2.PointCP('C', 0, 0);
    	}
    	else if (design == 2){
    		Design3.PointCP[] testing = (Design3.PointCP[]) points;
    		Design3.PointCP testPoint = new Design3.PointCP('P', 0, 0);
    	}
    	else {
    		Design6.PointCP[] testing = (Design6.PointCP[]) points;
    		
    		/**
    		* Since PointCartesian and PointPolar share an interface and getDistance uses the general interface for
    		* its implementation, we can use either PointCartesian or PointPolar for this test. PointCartesian
    		* was selected for no particular reason.
    		*/
    		
    		Design6.PointCP testPoint = new Design6.PointCartesian(0, 0);
    	}
    	
    	TestAction results = new TestAction("Calculate Distance");
    	long start, end;
    	
    	for (int i = 0; i < testing.length; i++) {
    		start = System.nanoTime();
    		testing[i].getDistance(testPoint);
    		end = System.nanoTime() - start;
    		results.addTime(end);
    	}
    	
    	return results;
    }
    
    private static TestAction testRotate(int design, Object[] points) { // Using generic Object class before casting
    	if (design == 1) Design2.PointCP[] testing = (Design2.PointCP[]) points;
    	else if (design == 2) Design3.PointCP[] testing = (Design3.PointCP[]) points;
    	else Design6.PointCP[] testing = (Design6.PointCP[]) points;
    	TestAction results = new TestAction("Rotate Point");
    	long start, end;
    	
    	for (int i=0; i < testing.length; i++) {
    		start = System.nanoTime();
    		testing[i].rotatePoint(30); // Rotate 30 degrees
    		end = System.nanoTime() - start;
    		results.addTime(end)
    	}
    	
    	return results;
    }
    
    private static TestAction testCoordinates(int design, Object[] points, char type) { // Using generic Object class before casting
    	if (design == 1) Design2.PointCP[] testing = (Design2.PointCP[]) points;
    	else if (design == 2) Design3.PointCP[] testing = (Design3.PointCP[]) points;
    	else Design6.PointCP[] testing = (Design6.PointCP[]) points;
    	
    	TestAction results = new TestAction("Generate Coordinate " + (type=='C') ? "Cartesian" : "Polar");
    	long start, end;
    	double xOrRho, yOrTheta;
    	
    	for (int i = 0; i < testing.length; i++) {
    		start = System.nanoTime();
    		if (type == 'C') {
    			xOrRho = testing[i].getX();
    			yOrTheta = testing[i].getY();
    		}
    		else {
    			xOrRho = testing[i].getRho();
    			yOrTheta = testing[i].getTheta();
    		}
    		end = System.nanoTime() - start;
    		results.addTime(end);
    	}
    }
    
    // TODO: Function to generate random coordinates of cartesian or polar type
    private static int generateCoord(char type) {
    	return 0;
    }
}
