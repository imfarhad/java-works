import java.util.concurrent.Semaphore;

class IncrementThread implements Runnable {
	
	private Semaphore sem;
	private String name;

	public IncrementThread(Semaphore sem, String name){
		this.sem = sem;
		this.name = name;
	}

	public void run(){

	try {
		System.out.println(this.name + ": Starting");
		System.out.println(this.name + ": Waiting to acquire....");
		sem.acquire();
		System.out.println(this.name + ": Acquired, working....");

		for(int i=0; i<10; i++){
			Shared.count++;
			System.out.println(this.name + ":" + Shared.count);
			Thread.sleep(1000);
		}
		System.out.println(this.name + ": Releasing....");
		sem.release();
		System.out.println(this.name + ": Released....");

	}
	catch(Exception e){

	}

	}
}

class Shared{
	static int count = 0;
}

class DecrementThread implements Runnable{
	private Semaphore sem;
	private String name;

	public DecrementThread(Semaphore sem, String name){
		this.sem = sem;
		this.name = name;
	}

	public void run(){
		try{
			System.out.println(this.name + ": Starting");			
			System.out.println(this.name + ": Waiting to Acquire...");
			sem.acquire();
			System.out.println(this.name + ": Acquired...");
			for (int i=10; i>0; i--) {
				Shared.count--;
				System.out.println(this.name + ":" + Shared.count);
				Thread.sleep(1000);
			}
			System.out.println(this.name + ": Releasing....");
			sem.release();
			System.out.println(this.name + ": Released....");

		}
		catch(Exception e){

		}
	}
}

class SemaphoreDemo {
	public static void main(String[] args){
		Semaphore sem = new Semaphore(1);
		new Thread(new IncrementThread(sem, "A-Inc")).start();
		new Thread(new DecrementThread(sem, "B-Dec")).start();
	}
}