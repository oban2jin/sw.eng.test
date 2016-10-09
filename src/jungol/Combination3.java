package jungol;

import java.util.Collections;
import java.util.Vector;

public class Combination3 {
	public static Vector<Integer> pixedNo = new Vector<Integer>();
	
	public static void main(String[] args) {
		Vector<Integer> T = new Vector<Integer>();
		pixedNo.add(1); pixedNo.add(2); pixedNo.add(3); pixedNo.add(4);
		T.addAll(pixedNo);
		
		makeCombination(4, 8, 1+pixedNo.size()-getFixedNoCnt(4, 8, pixedNo), T);
		System.out.println("*****************************************************");
		makeCombination(3, 10, 4+3-2, T);
		System.out.println("*****************************************************");
		makeCombination(3, 10, pixedNo.size()+3-getFixedNoCnt(3, 10, pixedNo), T);
		System.out.println("*****************************************************");
		makeCombination(3, 10, 4+1-2, T);
		
		//Fixed : 1,2,3,4
		//3 ~ 10 : 3개 
	}
	
	public static boolean makeCombination(int s,int e,int pickedCnt, Vector<Integer> pickedList){
		if(pickedCnt < pickedList.size()){
			System.out.println("Error!!!!");
			return false;
		}
		
		if(pickedCnt == pickedList.size()){
			System.out.println(pickedList);
			return true;
		}
		
		for(int i=s; i<=e; i++){
			Vector<Integer> rslt = (Vector<Integer>)pickedList.clone();
			if(!rslt.contains(i)){
				rslt.add(i);
				if(!makeCombination(i+1, e, pickedCnt, rslt))
					break;	
			}
		}
		
		return true;
	}
	
	public static int getFixedNoCnt(int s,int e,Vector<Integer> pickedList){
		int fixednocnt = 0;
		
		for(int x:pickedList){
			if(s<=x && x<=e){
				fixednocnt++;
			}
		}
		
		return fixednocnt;
	}
}
