import java.util.Scanner;


/**
 * Test class for all designs
 */
public class PointCPTestGeneric {

    static final int RUNS = 100;
    static final int TEST_SIZE = 10000;
    static final int DESIGN = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int runs;
        int design;
        
        // Design selection
        System.out.println("Which design do you want to test?")
        System.out.println("1: Design 2");
        System.out.println("2: Design 3");
        System.out.println("3: Design 6");
        System.out.println("4: Full test (default)");
        System.out.println("0: Cancel\n");
        System.out.print(":");
        
        try {
        	design = scanner.nextInt();
        	if (design > 4 || design < 0) throw new Exception();
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
        
        System.out.print("What should be the test size?");
        
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
        
        
        // TODO statistics logic and the like
    }
    
    private static TestDesign testDesign(int design, int runs, int size) {
    	TestDesign results = new TestDesign();
    	long start, end;
    	
    	
    	for (int i = 0; i < runs; i++) {
    		if (design == 1) Design2.PointCP[] points = new Design2.PointCP[size];
    		else if (design == 2) Design3.PointCP[] points = new Design3.PointCP[size];
    		else Design6.PointCP[] points = new Design6.PointCP[size];
    		for (int k = 0; j < size; j++) {
    			if (design == 1) { // Design 2
    			}
    			else if (design == 2) { // Design 3
    			}
    			else { // Design 6
    			}
    		}
    	}
    	
    	return null;
    }
    
    private static TestAction testDistance(int design, Object[] points) { // Using generic Object class before casting
    	if (design == 1) {
    		Design2.PointCP[] testing = (Design2.PointCP) points;
    		Design2.PointCP testPoint = new Design2.PointCP('C', 0, 0);
    	}
    	else if (design == 2){
    		Design3.PointCP[] testing = (Design3.PointCP) points;
    		Design3.PointCP testPoint = new Design3.PointCP('P', 0, 0);
    	}
    	else {
    		Design6.PointCP[] testing = (Design6.PointCP) points;
    		Design6.PointCartesian testPoint = new Design6.PointCartesian(0, 0);
    	}
    	
    	TestAction results = new TestAction(testing.length, "Calculate Distance");
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
    // TODO
    	if (design == 1) Design2.PointCP[] testing = (Design2.PointCP) points;
    	else if (design == 2) Design3.PointCP[] testing = (Design3.PointCP) points;
    	else Design6.PointCP[] testing = (Design6.PointCP) points;
    	TestAction results = new TestAction(testing.length, "Rotating Point");
    	long start, end;
    	
    	for (int i=0; i < testing.length; i++) {
    		start = System.nanoTime();
    		testing[i].rotatePoint(30);
    		end = System.nanoTime() - start;
    		results.addTime(end)
    	}
    	
    	return results;
    }
    
    private static TestAction testCoordinates(int design, Object[] points, char type) { // Using generic Object class before casting
    	if (design == 1) Design2.PointCP[] testing = (Design2.PointCP) points;
    	else if (design == 2) Design3.PointCP[] testing = (Design3.PointCP) points;
    	else Design6.PointCP[] testing = (Design6.PointCP) points;
    	
    	TestAction results = new TestAction();
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
