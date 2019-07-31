import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class Sum implements Callable<Integer>{
	int stop;

	Sum(int v){
		this.stop = v;
	}

	public Integer call(){
		int sum = 0;
		for(int i=1; i<=stop; i++)
			sum += i;
		return sum;
	}
}

class Hypot implements Callable<Double>{
	double side1, side2;

	Hypot(double side1, double side2){
		this.side1 = side1;
		this.side2 = side2;
	}

	public Double call(){
		double hypot = 0;
		hypot = Math.sqrt(side1*side1 + side2*side2);
		return hypot;
	}
}

class Factorial implements Callable<Integer>{
	int num;

	Factorial(int num){
		this.num = num;
	}

	public Integer call(){
		int result = 1;
		if(this.num<=0)return result;
		for(int i=1; i<=num; i++)
			result *= i;
		return result;
	}
}

class CallableDemo{
	public static void main(String[] x){
		ExecutorService e = Executors.newFixedThreadPool(3);
		Future<Integer> f1;
		Future<Double> f2;
		Future<Integer> f3;

		System.out.println("Starting Main Thread");

		f1 = e.submit(new Sum(10));
		f2 = e.submit(new Hypot(3,4));
		f3 = e.submit(new Factorial(7));

		try{
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
		}
		catch(Exception ex){

		}

		e.shutdown();
		System.out.println("Main Thread - DONE");

	}
}