import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class Transform extends RecursiveAction{
	private int threshold;
	private double[] data;
	private int start;
	private int end;

	Transform(double[] vals, int s, int e, int t){
		data = vals;
		start = s;
		end = e;
		threshold = t;
	}

	protected void compute(){
		if((end-start) < threshold){
			for(int i=0; i<end; i++){
				if((data[i]%2)==0)
					data[i] = Math.sqrt(data[i]);
				else
					data[i] = Math.cbrt(data[i]);
//				System.out.format("%.4f ",data[i]);
			}
		}
		else{
//			System.out.println("############# Breaking down the task #############");
			int middle = (start + end) / 2;
			invokeAll(new Transform(data, start, middle, threshold), 
				new Transform(data, middle, end, threshold));
		}
	}	
}

class FJExperiment{
	public static void main(String[] x){
		int pLevel;
		int threshold;

		if(x.length != 2){
			System.out.println("Invalid Arguments");
			return;
		}

		pLevel = Integer.parseInt(x[0]);
		threshold = Integer.parseInt(x[1]);

		long beginT, endT;

		ForkJoinPool fjp = new ForkJoinPool(pLevel);
		double[] nums = new double[1000000];

		for(int i=0; i<nums.length; i++)
			nums[i] = (double)i;

		Transform task = new Transform(nums, 0, nums.length, threshold);
		beginT = System.nanoTime();
		fjp.invoke(task);
		endT = System.nanoTime();
		System.out.println("Available Processors: "+Runtime.getRuntime().availableProcessors());
		System.out.println("Level of Parallelism: "+pLevel);
		System.out.println("Sequential Threshold: "+threshold);
		System.out.println("Elapsed Time: "+(endT-beginT)+" ns");
		System.out.println();
	}
}