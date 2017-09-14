package model;
import utils.Counter;

public class ConcurrencyB implements Runnable {
	private int EXECUTION_LIMIT = 1000000;
	
	private Counter counter;
	
	public ConcurrencyB (Counter counter){
		this.counter = counter;
	}
	
	@Override
	public void run() {
		int localProgramCounter = 0;
		for(int i=0; i< this.EXECUTION_LIMIT; i++){
			synchronized(counter){
				localProgramCounter = counter.getProgramCounter();
				localProgramCounter++;
				counter.setProgramCounter(localProgramCounter);
			}
		}
	}
	
}