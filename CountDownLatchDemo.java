import java.util.concurrent.CountDownLatch;
class CountDownLatchDemo{
	public static void main(String[] x){
		CountDownLatch cdl = new CountDownLatch(2);
		System.out.println("Main thread is starting");

		new Thread(new MyThread(cdl)).start();

		try{
			cdl.await();
		}
		catch(Exception e){

		}
		System.out.println("Done");

	}
}

class MyThread implements Runnable{
	
	CountDownLatch latch;

	public MyThread(CountDownLatch cdl){
		this.latch = cdl;
	}
	
	public void run(){
		for(int i=0; i<10; i++){
			System.out.println(i);
			latch.countDown();
		}
	}
}