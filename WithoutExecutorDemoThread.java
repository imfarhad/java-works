import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WithoutExecutorThread implements Runnable{
	String name;
//	CountDownLatch latch;

	WithoutExecutorThread(/*CountDownLatch c,*/ String s){
//		this.latch = c;
		this.name = s;
	}

	public void run(){
		for(int i=0; i<5; i++){
			System.out.println(name+" "+i);
//			latch.countDown();
		}
	}
}

class WithoutExecutorDemoThread{
	public static void main(String[] s){
/*		
		CountDownLatch c1 = new CountDownLatch(5);
		CountDownLatch c2 = new CountDownLatch(5);
		CountDownLatch c3 = new CountDownLatch(5);
		CountDownLatch c4 = new CountDownLatch(5); */

//		ExecutorService es = Executors.newFixedThreadPool(2);

		System.out.println("Starting the main thread");
/*
		es.execute(new ExecutorThread(c1,"A"));
		es.execute(new ExecutorThread(c2,"B"));
		es.execute(new ExecutorThread(c3,"C"));
		es.execute(new ExecutorThread(c4,"D"));
*/

/*		new Thread(new ExecutorThread(c1,"A")).start();
		new Thread(new ExecutorThread(c2,"B")).start();
		new Thread(new ExecutorThread(c3,"C")).start();
		new Thread(new ExecutorThread(c4,"D")).start();
		try{
			c1.await();
			c2.await();
			c3.await();
			c4.await();
		}
		catch(Exception e){

		}
*/

		new Thread(new WithoutExecutorThread("A")).start();
		new Thread(new WithoutExecutorThread("B")).start();
		new Thread(new WithoutExecutorThread("C")).start();
		new Thread(new WithoutExecutorThread("D")).start();

//		es.shutdown();
		System.out.println("Main Thread - Done");
	}
}