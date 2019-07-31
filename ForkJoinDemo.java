import java.util.concurrent.RecursiveAction;

class SqrtTransformAction extends RecursiveAction{
	final int sqrtThreshold = 1000;
	double[] data;
	int start, end;

	SqrtTransformAction(double[] vals, int s, int e){
		this.data = vals;
		this.start = s;
		this.end = e;
	}

	protected void compute(){
		if((end-start) < sqrtThreshold){
			for(int i=start; i<end; i++){
				data[i] = Math.sqrt(data[i]);
//				System.out.format("%.4f ",data[i]);
			}
		}
		else{
//			System.out.println("############# Breaking down the task #############");
			int middle = (start + end) / 2;
			invokeAll(new SqrtTransformAction(data, start, middle), 
				new SqrtTransformAction(data, middle, end));
		}
	}

}

class ForkJoinDemo{
	
	public static void main(String[] x){

//		double[] nums = new double[100000];
		double[] nums = new double[5000];
		for(int i=0; i<nums.length; i++){
			nums[i] = (double)i;
		}

		System.out.println("Normal Operation Start: "+System.nanoTime());
		for(int i=0; i<nums.length; i++){
			System.out.format("%.4f ", Math.sqrt(nums[i]));

		}
		System.out.println("\n\nNormal Operation End: "+System.nanoTime());

		System.out.println("ForkJoin Operation Start: "+System.nanoTime());
		SqrtTransformAction task = new SqrtTransformAction(nums, 0, nums.length);
		task.invoke();
		for(int i=0; i<5000; i++)
			System.out.format("%.4f ",nums[i]);
		System.out.println("\n\nForkJoin Operation End: "+System.nanoTime());
	}
}