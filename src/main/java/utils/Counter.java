package utils;

public class Counter {
	private int programCounter = 0;

	public int getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}
	
	synchronized public int safeGetProgramCounter() {
		return programCounter;
	}
	
	synchronized public void safeSetProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}
}
