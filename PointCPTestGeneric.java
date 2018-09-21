import java.util.Scanner;
import java.util.Random;


/**
 * Test class for all designs
 */
public class PointCPTestGeneric {


	// Testing Constants
    private static final int RUNS = 10;
    private static final int TEST_SIZE = 1000;
    private static final int DESIGN = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // User input scanner
        TStack<TestDesign> results = new TStack<TestDesign>(); // Used a stack in case of full test
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
        
        System.out.println("Running test on " + ((design == 4) ? ("all designs") : ((design < 3) ? ("Design " + (design+1)) : "Design 6")));
        
        
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
        
        System.out.print("What should be the test size? ");
        
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
        
    	if (design == 4) {
    		results.push(testDesign(3, runs, testSize));
    		results.push(testDesign(2, runs, testSize));
    		results.push(testDesign(1, runs, testSize));
    	}
    	else {
    		results.push(testDesign(design, runs, testSize));
    	}
    	
    	while (results.peek() != null) { // See all results that have been collected
    		processTest(results.pop());
    	}
    }
    
    private static void processTest(TestDesign design) {
    	String[] tests = {"Calculate Distance", "Rotate Point", "Generate Coordinate Cartesian", "Generate Coordinate Polar"};
    	System.out.println("Data for " + design.getDesignName() + "...");
    	
    	// Spit out all test statistics
    	for (byte i = 0; i < tests.length; i++){
    		String test = tests[i];
    		System.out.printf("\tTest name: %s\n", test);
    		// Overall stats
    		System.out.println("\t\tTotal runtime: " + (long) design.getTotal(i));
    		System.out.println("\t\tAverage test time: " + design.getAverage(i));
    		System.out.println("\t\tBest test time: " + design.getTotalBest(i));
    		System.out.println("\t\tWorst test time: " + design.getTotalWorst(i));
    		System.out.println();
    		// Sample Stats
    		System.out.println("\t\tBest sample time: " + design.getSampleBest(i));
    		System.out.println("\t\tWorst sample time: " + design.getSampleWorst(i));
    		System.out.println("\t\tAverage sample time: " + design.getSampleAverage(i));
    		System.out.println();
    		System.out.println("\t\tAverage of median times: " + design.getMedianAverage(i));
    	}
    }
    
    private static TestDesign testDesign(int design, int runs, int size) {
    	TestDesign results = new TestDesign(design);
    	long start, end;
    	
    	
    	for (int i = 0; i < runs; i++) {
    		

    		Object[] points = new Object[size];

    		// Generate appropriate point instances for each design
    		for (int j = 0; j < size; j++) {
    			if (design == 1) { // Design 2
    				points[j] = new Design2.PointCP('P', generateCoord(), generateCoord());
    			}
    			else if (design == 2) { // Design 3
    				points[j] = new Design3.PointCP('C', generateCoord(), generateCoord());
    			}
    			else { // Design 6
    				if (j < (size/2)) {
    					points[j] = new Design6.PointPolar(generateCoord(), generateCoord());
    				}
    				else {
    					points[j] = new Design6.PointCartesian(generateCoord(), generateCoord());
    				}
    			}
    		}

    		results.addTest(testDistance(design, points));
			results.addTest(testRotate(design, points));
			results.addTest(testCoordinates(design, points, 'C'));
			results.addTest(testCoordinates(design, points, 'P'));
    	}
    	
    	return results;
    }
    
    private static TestAction testDistance(int design, Object[] points) { // Using generic Object class before casting
    	Object testPoint;
    	if (design == 1) {
    		testPoint = new Design2.PointCP('C', 0, 0);
    	}
    	else if (design == 2){
    		testPoint = new Design3.PointCP('P', 0, 0);
    	}
    	else {
    		/**
    		* Since PointCartesian and PointPolar share an interface and getDistance uses the general interface for
    		* its implementation, we can use either PointCartesian or PointPolar for this test. PointCartesian
    		* was selected for no particular reason.
    		*/
    		
    		testPoint = new Design6.PointCartesian(0, 0);
    	}
    	
    	TestAction results = new TestAction((byte) 0);
    	long start, end;
    	
    	for (int i = 0; i < points.length; i++) {
    		start = System.nanoTime();
    		if (design == 1) {
				((Design2.PointCP) points[i]).getDistance((Design2.PointCP) testPoint);
			}
			else if (design == 2) {
				((Design3.PointCP) points[i]).getDistance((Design3.PointCP) testPoint);
			}
			else {
				((Design6.PointCP) points[i]).getDistance((Design6.PointCP) testPoint);
			}
    		end = System.nanoTime() - start;
    		results.addTime(end);
    	}
    	
    	return results;
    }
    
    private static TestAction testRotate(int design, Object[] points) { // Using generic Object class before casting
    	TestAction results = new TestAction((byte) 1);
    	long start, end;
    	
    	for (int i=0; i < points.length; i++) {
    		start = System.nanoTime();
    		if (design == 1) {
				((Design2.PointCP) points[i]).rotatePoint(30);
			}
			else if (design == 2) {
				((Design3.PointCP) points[i]).rotatePoint(30);
			}
			else {
				((Design6.PointCP) points[i]).rotatePoint(30);
			}
    		end = System.nanoTime() - start;
    		results.addTime(end);
    	}
    	
    	return results;
    }
    
    private static TestAction testCoordinates(int design, Object[] points, char type) { // Using generic Object class before casting
    	TestAction results = new TestAction((type == 'C') ? (byte) 2 : (byte) 3);
    	long start, end;
    	double xOrRho, yOrTheta;
    	
    	for (int i = 0; i < points.length; i++) {
    		start = System.nanoTime();
    		if (type == 'C') {
    			if (design == 1) {
					xOrRho = ((Design2.PointCP) points[i]).getX();
					yOrTheta = ((Design2.PointCP) points[i]).getY();
				}
				else if (design == 2){
					xOrRho = ((Design3.PointCP) points[i]).getX();
					yOrTheta = ((Design3.PointCP) points[i]).getY();
				}
				else {
					xOrRho = ((Design6.PointCP) points[i]).getX();
					yOrTheta = ((Design6.PointCP) points[i]).getY();
				}
    		}
    		else {
    			if (design == 1) {
    				xOrRho = ((Design2.PointCP) points[i]).getRho();
    				yOrTheta = ((Design2.PointCP) points[i]).getTheta();
				}
				else if (design == 2) {
					xOrRho = ((Design3.PointCP) points[i]).getRho();
					yOrTheta = ((Design3.PointCP) points[i]).getTheta();
				}
				else {
					xOrRho = ((Design6.PointCP) points[i]).getRho();
					yOrTheta = ((Design6.PointCP) points[i]).getTheta();
				}
    		}
    		end = System.nanoTime() - start;
    		results.addTime(end);
    	}
    	return results;
    }

    private static int generateCoord() {
    	Random rand = new Random();

		return rand.nextInt(360)+1;
    }
}
