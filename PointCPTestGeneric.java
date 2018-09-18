import java.util.Scanner;


/**
 * Generic test class to base other test classes on.
 */
public class PointCPTestGeneric {

    static final int RUNS = 10000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many tests to run (0 to exit): ");
        int runs;

        // TODO: Maybe some better error checking? Idk it doesn't feel like it needs to be any more robust...
        try {
            runs = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.printf("Error occurred, using default value %d... \n");
            runs = RUNS;
        }

        if (runs < 1) return; // Just in case someone wants to be cheeky and use negative values

        // Real talk, why does Java not have unsigned integers? It would kinda solve this whole problem
        // but in any case, I digress...

        runTests(runs);
    }

    private static void runTests(int runs) {
        // TODO: Define what the tests should actually be and then, you know, code them.

        long[] runTimes = new long[runs]; // Store the times for all the runs so we can calc the median
        long min, max; // Store the first trial times here, then compare every subsequent trial.

        // Test code starts here


    }
}
