public class TestDesign {
	TestAction[] tests;
	int testsAdded;
	
	public TestDesign(int tests) {
		tests = new TestAction[tests];
		testsAdded = 0;
	}
	
	public void addTest(TestAction test){
		tests[testsAdded] = test;
		testsAdded++;
	}
}