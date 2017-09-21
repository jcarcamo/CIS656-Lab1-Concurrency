import java.util.ArrayList;
import java.util.List;

import model.ConcurrencyA;
import model.ConcurrencyB;
import utils.Counter;

public class ConcurrencyApp{
	enum TYPE{
		A,
		B;
		
		private ConcurrencyA concurrencyA;
		private ConcurrencyB concurrencyB;
		
		public void setCounter (Counter counter){
			this.concurrencyA = new ConcurrencyA(counter);
			this.concurrencyB = new ConcurrencyB(counter);
		}
		
		public ConcurrencyA getConcurrencyA(){
			return concurrencyA;
		}
		public ConcurrencyB getConcurrencyB(){
			return concurrencyB;
		}
	}
	
	public void runConcurrencyTest (int threadCount, TYPE type, Counter sharedCounter){
		sharedCounter.setProgramCounter(0);
		type.setCounter(sharedCounter);
		List<Thread> threads = new ArrayList<>();
		for(int i=0; i< threadCount; i++){
			switch(type){
			case A:
				threads.add(new Thread(type.getConcurrencyA()));
				break;
			case B:
				threads.add(new Thread(type.getConcurrencyB()));
				break;
			}
			
		}
		long startTime = System.currentTimeMillis();	
		for(Thread thread:threads){
			thread.start();
		}

		try {
			for(Thread thread:threads){
				thread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long finalTime = System.currentTimeMillis() - startTime;
		StringBuilder outputSb = new StringBuilder();
		switch(type){
		case A:
			outputSb.append("Program A:").append('\n');
			break;
		case B:
			outputSb.append("Program B:").append('\n');
			break;
		}
		
		outputSb.append("Number of threads: ").append(threadCount).append('\n');
		outputSb.append("Execution Time: ").append(finalTime).append(" milliseconds").append('\n');
		outputSb.append("Counter Value: ").append(sharedCounter.getProgramCounter()).append('\n');
		outputSb.append("********************************").append("\n\n\n");
		System.out.println(outputSb.toString());

		
		
	}
	public static void main(String[] args) {
		Counter sharedCounter = new Counter();
		int threadCount = 0;
		
		try{
			threadCount = Integer.parseInt(args[0]);
		}catch(NumberFormatException nfe){
			System.out.println("First Argument not a number");
			System.exit(1);
		}catch(IndexOutOfBoundsException ioe){
			System.out.println("No argument provided");
			System.exit(1);
		}catch(Exception e){
			System.out.println("Uknown exception");
			e.printStackTrace();
			System.exit(1);
		}
		
		ConcurrencyApp app = new ConcurrencyApp();
		TYPE type = TYPE.A;
		app.runConcurrencyTest(threadCount, type,sharedCounter);
		type = TYPE.B;
		app.runConcurrencyTest(threadCount, type,sharedCounter);
		type = TYPE.A;
		app.runConcurrencyTest(threadCount, type,sharedCounter);
		type = TYPE.B;
		app.runConcurrencyTest(threadCount, type,sharedCounter);
		type = TYPE.A;
		app.runConcurrencyTest(threadCount, type,sharedCounter);
		type = TYPE.B;
		app.runConcurrencyTest(threadCount, type,sharedCounter);

    }
}