import java.util.concurrent.Semaphore;

class Q{
	int n;

	Semaphore conSem = new Semaphore(0); //makes intially unavailable
	Semaphore prodSem = new Semaphore(1);

	void produce(int n){
		try{
			prodSem.acquire();
		}
		catch(Exception e){

		}

		this.n = n;
		System.out.println("Producer Put: "+this.n);
		conSem.release();
	}

	void consume(){
		try{
			conSem.acquire();
		}
		catch(Exception e){

		}
		System.out.println("Consumer Get: "+ this.n);
		prodSem.release();
	}
}

class Producer implements Runnable{
	Q q;
	Producer(Q q){
		this.q = q;
	}
	public void run(){
		for(int i=0; i<20; i++)q.produce(i);
	}
}

class Consumer implements Runnable{
	Q q;
	Consumer(Q q){
		this.q = q;
	}
	public void run(){
		for(int i=0; i<20; i++)q.consume();
	}
}

class ProducerConsumer{
	
	public static void main(String[] x){
		Q q = new Q();
		new Thread(new Producer(q),"Producer").start();
		new Thread(new Consumer(q),"Consumer").start();		
	}

}