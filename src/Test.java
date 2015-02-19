import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) throws InterruptedException, IOException {
		int Numthreads = Runtime.getRuntime().availableProcessors();
		if(args.length > 1) {
			Numthreads = Integer.parseInt(args[1]);
		}
		int limit = 4000;
		if(args.length > 0) {
			limit = Integer.parseInt(args[0]);
		}
		System.out.println("computing Pi with " + Numthreads + " Threads...");
		PiThread[] threads = new PiThread[Numthreads];
		
		long sTime = System.currentTimeMillis();
		int last = 0;
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new PiThread(last, last + limit/threads.length-1, limit);
			threads[i].start();
			last = last + limit/threads.length;
		}
		int deadThreads = 0;
		while(deadThreads != threads.length) {
			deadThreads = 0;
			for(PiThread t : threads) {
				if(t.getResult() != null)
					deadThreads++;
			}
		}
		BigDecimal pi = BigDecimal.ZERO;
		for(PiThread t : threads) {
			pi = pi.add(t.getResult());
		}
		System.out.println("computed the first " + limit + " digets of pi in " + (System.currentTimeMillis() - sTime) + " ms! the first digets are: " + pi.toString().substring(0, 10));
		System.out.println("writing to pi.txt ...");
		FileWriter fw = new FileWriter("pi.txt");
		fw.write(pi.toString().substring(0, limit));
		fw.close();
		
		
		System.out.println("finished!");
	}
}