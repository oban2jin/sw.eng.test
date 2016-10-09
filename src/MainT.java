import java.util.HashSet;
import java.util.Scanner;
   
public class  MainT {
    public static int N;
    public static int C;
    public static HashSet<Integer> cointype = new HashSet<Integer>();
    public static int[] mincoin;
    public static int ctotal;
    
    public static int unit = 5000;
    
    public static void main(String args[]) throws Exception {
 
      Scanner sc = new Scanner(System.in);
      
      C = sc.nextInt();
      
      for(int x= 0; x< C ; x++){
    	  int c = sc.nextInt();
    	  cointype.add(c);
      }
      
      N = sc.nextInt();
      mincoin = new int[N+1];
      
      //Debug Codde
//      System.out.println(C+"/"+N);
//      System.out.println(cointype);
      
//      Input Data
//      4
//      14 12 3 3 
//      21983
//
//
//      CPU Result
//      1571
      
      int x = N/unit;
      int y = N%unit;
      int z = 0;
      
      for(int i=1;i<=x;i++){
//    	  System.out.print(i*unit+"/");
//    	  System.out.println(getMinCoinCount(i*unit));
    	  getMinCoinCount(i*unit);
      }
      
      int rslt=0;
	  rslt = getMinCoinCount(N);
	  
      if(rslt<0){
    	  System.out.println("impossible");
      }else{
    	  System.out.println(rslt);
      }
      
//      
//      for(int x : mincoin){
//    	  System.out.println(x);
//      }
      
    } //End of Main Method
    
    public static int getMinCoinCount(int n){
//    	System.out.println(n+": is called.");
    	
    	if(cointype.contains(n)){
    		mincoin[n] = 1;
    	}
    	
    	//지불최소방법 배열이 초기값이 아닌경우 무조건 리턴한다.
    	if(mincoin[n]!=0){
    		return mincoin[n];
    	}
    	
    	int min = Integer.MAX_VALUE;   	
		for(int coin : cointype){
			if(n-coin>0){
				int temp = getMinCoinCount(n-coin);
				mincoin[n-coin]= temp;
				if(temp>0)
					min = Math.min(temp,min);
			}
		}
		
		if(min == Integer.MAX_VALUE){
			min = -1;
		}else{
			min = min+1;
		}
		
		mincoin[n]= min;
    	
    	return min;
    }
    
} // End of Class