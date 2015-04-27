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
		//사용가능 동전 타입 설정
		coins.add(1);
		coins.add(5);
		coins.add(10);
		coins.add(15);
		coins.add(20);
		coins.add(25);
		
		System.out.println("\nThe Answer="+findMinCoins(40));
		long end = System.nanoTime();
		
		System.out.println("걸린시간:"+(end-start)+" ns");
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
				//최소 지불 갯수를 알고 있는지 확인
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
