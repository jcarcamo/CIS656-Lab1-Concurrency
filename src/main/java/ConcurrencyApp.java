import java.util.ArrayList;
import java.util.List;

import model.ConcurrencyA;
import model.ConcurrencyB;
import utils.Counter;

public class ConcurrencyApp{
	
	public static void main(String[] args) {
		Counter sharedCounter = new Counter();
		
		int threadCount = 0;
		List<Thread> threads = new ArrayList<>();
		
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
		
		for(int i=0; i< threadCount; i++){
			threads.add(new Thread(new ConcurrencyA(sharedCounter)));
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
		outputSb.append("Program A:").append('\n');
		outputSb.append("Number of threads: ").append(threadCount).append('\n');
		outputSb.append("Execution Time: ").append(finalTime).append(" milliseconds").append('\n');
		outputSb.append("Counter Value: ").append(sharedCounter.getProgramCounter()).append('\n');
		outputSb.append("********************************").append("\n\n\n");
		
		threads.clear();
		sharedCounter.setProgramCounter(0);
		for(int i=0; i< threadCount; i++){
			threads.add(new Thread(new ConcurrencyB(sharedCounter)));
		}
		
		startTime = System.currentTimeMillis();	
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
		finalTime = System.currentTimeMillis() - startTime;
		outputSb.append("Program B:").append('\n');
		outputSb.append("Number of threads: ").append(threadCount).append('\n');
		outputSb.append("Execution Time: ").append(finalTime).append(" milliseconds").append('\n');
		outputSb.append("Counter Value: ").append(sharedCounter.getProgramCounter());
		System.out.println(outputSb.toString());

    }
}