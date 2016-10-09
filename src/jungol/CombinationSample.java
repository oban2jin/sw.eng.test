package jungol;

import java.util.ArrayList;
import java.util.Vector;

public class CombinationSample {
	
	public static void main(String argv[]){
		combination(1, 4, 2, new Vector<Integer>());
	}

	public static void combination(int s, int e,int picked, Vector<Integer> pickedlist){
		//s~e까지의 숫자중 picked갯수 조합을 출력한다. 
		//pickedlist: 이전까지 조합된 리스트
		
		//모든갯수 조합완료된 경우  		
		if(picked==pickedlist.size()){
			System.out.println(pickedlist);
			return;
		}

		for(int x=s;x<=e;x++){
			Vector<Integer>rslt = (Vector<Integer>) pickedlist.clone();
			rslt.add(x);
			combination(x+1,e,picked,rslt);
		} 
	}
}
