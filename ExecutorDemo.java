import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorThread implements Runnable{
	String name;
	CountDownLatch latch;

	ExecutorThread(CountDownLatch c, String s){
		this.latch = c;
		this.name = s;
	}

	public void run(){
		for(int i=0; i<5; i++){
			System.out.println(name+" "+i);
			latch.countDown();
		}
	}
}

class ExecutorDemo{
	public static void main(String[] s){
		CountDownLatch c1 = new CountDownLatch(5);
		CountDownLatch c2 = new CountDownLatch(5);
		CountDownLatch c3 = new CountDownLatch(5);
		CountDownLatch c4 = new CountDownLatch(5);

		ExecutorService es = Executors.newFixedThreadPool(2);

		System.out.println("Starting the main thread");

		es.execute(new ExecutorThread(c1,"A"));
		es.execute(new ExecutorThread(c2,"B"));
		es.execute(new ExecutorThread(c3,"C"));
		es.execute(new ExecutorThread(c4,"D"));

		try{
			c1.await();
			c2.await();
			c3.await();
			c4.await();
		}
		catch(Exception e){

		}

		es.shutdown();
		System.out.println("Main Thread - Done");



	}
}