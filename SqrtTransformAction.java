import java.util.concurrent.RecursiveAction;

class SqrtTransformAction extends RecursiveAction{
	final int sqrtThreshold = 1000;
	double[] data;
	int start, end;

	SqrtTransformAction(double[] data, int start, int end){
		this.data = data;
		this.start = start;
		this.end = end;
	}

	protected void compute(){
		if((end-start) < sqrtThreshold){
			for(int i=0; i<end; i++){
				data[i] = Math.sqrt(data[i]);
			}
		}
		else{
			int middle = (start+end)/2;
			invokeAll(new SqrtTransformAction(data, start, middle), 
				new SqrtTransformAction(data, middle, end));
		}
	}

}

class ForkJoinDemo{
	
	public static void main(String[] x){

		double[] nums = new double[100000];
		for(int i=0; i<nums.length; i++){
			nums[i] = (double)i;
			System.out.println(nums[i]);
		}
	}
}