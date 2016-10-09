package sw.eng.test;

public class DynamicBackPack {
	private int[] wi;
	private int[] ci;
	private int N=4;
	private int W=14;
	private int[][] DS;
	
	public static void main(String[] args) {
		 
		DynamicBackPack dbp = new DynamicBackPack();
	    
		System.out.println(dbp.ds(dbp.N, dbp.W));
		
		for(int x = 1; x<dbp.N+1;x++){
			for(int y = 1; y< dbp.W+1; y++ ){
				System.out.print(dbp.DS[x][y]);
				System.out.print(",");
			}
			System.out.println("");
		}
	 
	 
	}
	
	public DynamicBackPack(){
		this.wi = new int[] { 2, 5, 10, 3};
		this.ci = new int[] { 40,110,200,50 };
		
		DS = new int[N+1][W+1];
		
 		for(int x = 1; x<N+1;x++){
			for(int y = 1; y< W+1; y++ ){
				this.DS[x][y]=-1;
			}
		}
	}
	
	public int ds(int i, int w){
		
		if(i==0)
			return 0;
		
		if(this.DS[i][w]!=-1)
			return this.DS[i][w];
		
		this.DS[i][w] = this.ds(i-1, w);
		
		if(w-this.wi[i-1]>=0){
			this.DS[i][w] = Math.max(this.DS[i][w], this.ds(i-1, w-this.wi[i-1])+this.ci[i-1]);
		}
		
		return this.DS[i][w];
	}
	
	
	
	

}
