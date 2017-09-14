package model;
import utils.Counter;

public class ConcurrencyA implements Runnable {
	private int EXECUTION_LIMIT = 1000000;
	
	private Counter counter;
	
	public ConcurrencyA (Counter counter){
		this.counter = counter;
	}
	
	@Override
	public void run() {
		int localProgramCounter = 0;
		for(int i=0; i< this.EXECUTION_LIMIT; i++){
			localProgramCounter = counter.getProgramCounter();
			localProgramCounter++;
			counter.setProgramCounter(localProgramCounter);
		}
	}
	
}