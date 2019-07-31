import java.util.concurrent.Phaser;

class PhaserThread implements Runnable{
	Phaser phaser;
	String name;

	PhaserThread(Phaser phaser, String name){
		this.phaser = phaser;
		this.name = name;
		this.phaser.register();
	}

	public void run(){
		System.out.println("Thread: "+this.name+" Beginning Phase One");
		phaser.arriveAndAwaitAdvance();

		try{
			Thread.sleep(100);
		}
		catch(Exception e){
		}

		System.out.println("Thread: "+this.name+" Beginning Phase Two");
		phaser.arriveAndAwaitAdvance();

		try{
			Thread.sleep(100);
		}
		catch(Exception e){
		}

		System.out.println("Thread: "+this.name+" Beginning Phase Three");
		phaser.arriveAndDeregister();
	}
}

class PhaserDemo{
	public static void main(String[] x){
		Phaser phaser = new Phaser(1);
		int curPhase;

		System.out.println("Starting");
		new Thread(new PhaserThread(phaser,"A")).start();
		new Thread(new PhaserThread(phaser,"B")).start();
		new Thread(new PhaserThread(phaser,"C")).start();

		curPhase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase "+curPhase+" Complete");

		curPhase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase "+curPhase+" Complete");

		curPhase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase "+curPhase+" Complete");

		phaser.arriveAndDeregister();

		if(phaser.isTerminated())
			System.out.println("Phaser is Terminated");		

	}
}