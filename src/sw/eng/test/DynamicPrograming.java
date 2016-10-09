package sw.eng.test;

import java.util.HashMap;
import java.util.Vector;

public class DynamicPrograming {
	
	Vector<Integer> coins = new Vector<Integer>();
	HashMap<Integer,Integer> mincoins = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		DynamicPrograming dp = new DynamicPrograming();
		dp.doSomething();
	}
	
	public void doSomething(){
		long start = System.nanoTime();
		coins.add(1);
		coins.add(5);
		coins.add(10);
		coins.add(15);
		coins.add(20);
		coins.add(25);
		
		System.out.println("\nThe Answer="+findMinCoins(40));
		long end = System.nanoTime();
		
		System.out.println("Elapsed Time(ns):"+(end-start)+" ns");
	}
	
	public int findMinCoins(int totalPayAmt){
		int rslt = 0;
		int x1=0;
		int temp=1;
		
		if(totalPayAmt==0){
			rslt = 0;
		}
		else if(totalPayAmt==1){
			rslt = 1;
		}else{
			temp=Integer.MAX_VALUE;
			for(int coin : coins){
				if(totalPayAmt-coin>=0){
					if(mincoins.containsKey(totalPayAmt-coin)){
						x1 = 1 + mincoins.get(totalPayAmt-coin);
					}else{
						x1 = 1 + findMinCoins(totalPayAmt-coin);				
					}
					temp = Math.min(x1, temp);
				}
			}
			rslt = temp;
		}
		mincoins.put(totalPayAmt, rslt);
		return rslt;
	}

}
