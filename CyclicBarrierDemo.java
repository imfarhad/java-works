import java.util.concurrent.CyclicBarrier;

class CBThread implements Runnable{
	CyclicBarrier cbar;
	String name;

	public CBThread(CyclicBarrier c, String name){
		this.cbar = c;
		this.name = name;
	}

	public void run(){
		System.out.println(this.name);

		try{
			cbar.await();
		}
		catch(Exception e){

		}
	}
}

class BarAction implements Runnable{
	public void run(){
		System.out.println("Barrier Reached");
	}
}

class CyclicBarrierDemo{
	public static void main(String[] x){
		CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
		System.out.println("Starting");

		new Thread(new CBThread(cb, "A")).start();
		new Thread(new CBThread(cb, "B")).start();
		new Thread(new CBThread(cb, "C")).start();
		new Thread(new CBThread(cb, "D")).start();
		new Thread(new CBThread(cb, "E")).start();
		new Thread(new CBThread(cb, "F")).start();
	}
}