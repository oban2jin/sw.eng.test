package sw.eng.test;

public class Factorial {
	private static int N;
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		Factorial f = new Factorial(5);
		System.out.println(f.getFactorialNumber(f.N));
		long endTime = System.nanoTime();
		System.out.println("time ="+(endTime-startTime)+"ns");
	}
	
	public Factorial(int N){
		super();
		this.N = N;
	}
	
	public int getFactorialNumber(int N){
		if(N == 1){
			return N;
		}else{
			return N*this.getFactorialNumber(N-1);
		}
	}
	
	public String toString(){
		StringBuffer sf = new StringBuffer();
		return sf.append(this.N).toString();
	}

}
