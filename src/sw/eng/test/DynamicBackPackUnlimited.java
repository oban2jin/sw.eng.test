package sw.eng.test;

public class DynamicBackPackUnlimited {
	private int[] wi;
	private int[] ci;
	private int N=4;
	private int W=9;
	private int[] DS;
	
	public static void main(String[] args) {
		 
		DynamicBackPackUnlimited dbp = new DynamicBackPackUnlimited();
	    
		System.out.println(dbp.ds(dbp.W));
		
		for(int y = 1; y< dbp.W+1; y++ ){
			System.out.print(dbp.DS[y]);
			System.out.print(",");
		}
	 
	 
	}
	
	public DynamicBackPackUnlimited(){
		this.wi = new int[] {2, 5, 10, 3};
		this.ci = new int[] {40,110,200,50};
		
		DS = new int[W+1];
	}
	
	public int ds(int w){
		
		if(this.DS[w] > 0)
			return DS[w];

    	int MAX=0;
    	
	    for(int i=0;i<this.ci.length;i++){
	    	if(w-this.wi[i]>=0){
	    		MAX = Math.max(this.ds(w-this.wi[i]) + this.ci[i],MAX);
	    	}
	    }
	    
		DS[w] = MAX;
	    
		return MAX;
	}
	
	
}
