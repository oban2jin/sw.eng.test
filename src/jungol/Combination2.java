package jungol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class Combination2 {
	public static int K;
	public static int S;
	public static int E;
	public static int N;
	
	public static int[] spcase;
	public static int[] epcase;
	public static int[] npcase;
	public static int[] pcase;
	
	public static String[] stpcase;
	
	public static Vector<Integer> com = new Vector<Integer>();
	public static ArrayList<Vector<Integer>> rslt;
	public static ArrayList[] allcase; 

	public static ArrayList<Vector<Integer>> X = new ArrayList<Vector<Integer>>();
	
	public static void main(String[] args) {

		K = 12;
		N = 7;
		
		pcase = new int[K+1];
		stpcase = new String[K+1];
		for(int x=1;x<K+1;x++)
			stpcase[x]="-";
		
		spcase = new int[N+1];
		epcase = new int[N+1];
		npcase = new int[N+1];
		allcase = new ArrayList[N+1];
		
	    spcase[1] = 1; epcase[1] = 8; npcase[1] = 4;
	    spcase[2] = 6; epcase[2] = 10; npcase[2] = 4;
	    spcase[3] = 2; epcase[3] = 12; npcase[3] = 6;
	    spcase[4] = 9; epcase[4] = 12; npcase[4] = 2;
	    spcase[5] = 4; epcase[5] = 6; npcase[5] = 1;
	    spcase[6] = 1; epcase[6] = 4; npcase[6] = 1;
	    spcase[7] = 11; epcase[7] = 11; npcase[7] = 1;
	    
		for(int x=1;x<N+1;x++){
			rslt =  new ArrayList<Vector<Integer>>();
			combination(spcase[x],epcase[x],npcase[x],com,rslt);
			allcase[x] = rslt;
		}
		
//		for(int i=1;i<allcase.length;i++){
//			ArrayList<Vector<Integer>> T = allcase[i];
//			for(Vector<Integer> x : T){
//				System.out.println(x);
//			}
////			System.out.println("********************************");
//		}
		
		checkAllCombination(1);
//		System.out.println(X);
		if(X.size()==0){
			System.out.println("NONE");
		}else{
			Vector<Integer> xx = X.get(0);
			for(int x : xx){
				stpcase[x]="#";
			}
			for(int x=1;x<K+1;x++){
				System.out.print(stpcase[x]);
			}
			
		}
		
	}

//	public static void checkAllCombination2(int n){
//		if(n==N+1){
//			Vector<Integer> xx = new Vector<Integer>();
//			for(int x=1;x<K+1;x++){
//				if(pcase[x]==1){
//					xx.add(x);
//				}
//			}
//			T.add(xx);
//			return;
//		}
//		
//		ArrayList<Vector<Integer>> rslt = allcase[n];
//		for(Vector<Integer> T : rslt){
//			for(int i=0;i<T.size();i++){
//				pcase[T.get(i)] = 1;
//			}
//			checkAllCombination2(n+1);
//			//검증 배열 초기
//			for(int i=0;i<T.size();i++){
//				pcase[T.get(i)] = 0;
//			}
//		}
//	}
	
	public static boolean checkAllCombination(int n){
		
		if(n==N+1){
			boolean t = true;
			//검증해봐야
			for(int x=1;x<N+1;x++){
				int s = spcase[x];
				int e = epcase[x];
				int cnt = npcase[x];
				if(!verifyPcase(s, e, cnt)){
					t=false;
					break;
				}
			}
			
			if(t){
				Vector<Integer> xx = new Vector<Integer>();
				for(int x=1;x<K+1;x++){
					if(pcase[x]==1){
						xx.add(x);
					}
				}
				X.add(xx);
				return true;
			}
			
			return false;
		}
		
		ArrayList<Vector<Integer>> rslt = allcase[n];
		for(Vector<Integer> T : rslt){
			for(int i=0;i<T.size();i++){
				pcase[T.get(i)] = 1;
			}
			
			if(checkAllCombination(n+1))
				return true;			
			
			for(int i=0;i<T.size();i++){
				pcase[T.get(i)] = 0;
			}
		}
		return false;
	}
	
	public static void combination(int s, int e,int picked, Vector<Integer> pickedlist, ArrayList<Vector<Integer>> allcombination){
		//모든갯수 조합완료된 경우  		
		if(picked==pickedlist.size()){
//			System.out.println(pickedlist);
			allcombination.add(pickedlist);
			return;
		}

		for(int x=s;x<=e;x++){
			Vector<Integer>rslt = (Vector<Integer>) pickedlist.clone();
			rslt.add(x);
			combination(x+1,e,picked,rslt,allcombination);
		} 
	}
	
	public static boolean verifyPcase(int s, int e, int cnt){
		boolean rslt = false;
		int tmp = 0;
		
		if(e<s)
			return false;
		
		for(int x=s;x<=e;x++){
			if(pcase[x] == 1){
				tmp++;
				if(tmp>cnt)
					break;
			}
		}
		
		if(tmp == cnt)
			rslt = true;
		
		return rslt;
	}
}
