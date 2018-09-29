public class TestAction {
	private long[] times;
	private int tests;
	private long median;
	private long best;
	private long worst;
	private String actionName;
	
	public TestAction(int sizeOfTest, actionName){
		this.times = new long[sizeOfTest];
		this.tests = 0;
		this.worst = -1;
		this.actionName = actionName;
	}
	
	public void addTime(long time){
		times[tests] = time;
		if (best > time) best = time;
		if (worst == -1 || worst < time) worst = time;
		tests++;
	}
	
	public long[] getTimes() {
		return times;
	}
	
	public int getTests() {
		return tests;
	}
	
	public long getWorst() {
		return worst;
	}
	
	public long getBest() {
		return best;
	}
	
	public String getTestName() {
		return actionName;
	}
}