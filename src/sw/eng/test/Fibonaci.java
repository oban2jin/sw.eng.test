package sw.eng.test;

public class Fibonaci {

	private long[] fno;
	public static void main(String[] args) {
		
		Fibonaci fb = new Fibonaci();
		int N = 10;
		fb.fno = new long[N+1];
		
		long startTime = System.nanoTime();
		
//		System.out.println(fb.recursionCall(N));
		fb.dynamicAlgorithm(N);
		System.out.println(fb.fno[N]);
		
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
	}
	
	public int recursionCall(int N){
		if(N<=2){
			return 1;
		}else{
			return this.recursionCall(N-1)+this.recursionCall(N-2);
		}
	}
	
	public void dynamicAlgorithm(int N){
		
		for(int x= 1 ; x <= N ; x++){
			if(x<=2){
				this.fno[x] = 1;
			}else{
				this.fno[x] = fno[x-1]+fno[x-2]; 				
			}
		}
	}

}
