package pretest.magicinjection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pain implements Comparable<Pain>{
	public int s,e;
	public long sum;
	public ArrayList<Integer> days;
	
	public Pain(int s,int e,long sum, int day){
		this.s=s; this.e=e; this.sum = sum;
		days = new ArrayList<Integer>();
		if(day>0) days.add(day);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(s+"/"+e+"/"+sum+"/"+days);
		return sb.toString();
	}
	
	@Override
	public int compareTo(Pain o) {
		for(int i=0;i<this.days.size();i++){
			if(this.days.get(i)<o.days.get(i)){
				return -1;
			}
		}
		return 0;
	}
	

}

public class Solution {
	public static final long INF = (long)1e13;
	public static int T,N,K;
	public static int[] exc;
	public static Pain[][][] cache;
	
	public static void main(String[] args) throws Exception{
	
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/pretest/magicinjection/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Pain ANS = null;
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			exc = new int[N+1];
			cache = new Pain[K+1][N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<N+1;n++){
				exc[n] = Integer.parseInt(st.nextToken());
			}
			
			ANS = getMinPainSum(1, N, K,-1);
			
			bw.write("#"+(t)+" "+ANS.sum+" "+ANS.days+" "+"\n");
			
		}
		bw.flush();
	}
	

	public static Pain getMinPainSum(int s,int e,int k,int days){
//		System.out.println(s+"/"+e+"/"+k+"/"+days);
		Pain minpainsum = new Pain(s,e,INF,days);
		
		if(e-s+1<k){
			return new Pain(s,e,INF,days);
		}
		
		if(k==0){
			return new Pain(s,e,calPainAmout(s, e),days);
		}
		
		if(cache[k][s][e]!=null)
			return cache[k][s][e];
		
		for(int day=s;day<e+1;day++){
			Pain T = getMinPainSum(s,day,0,day);
			if((day+1<=e)&&(true)){
				Pain T2 = getMinPainSum(day+1, e, k-1,-1);
				T.sum = T.sum+T2.sum;
				T.e = T2.e;
				T.days.addAll(T2.days);
			}
			
			System.out.println("minpainsum=>"+minpainsum);
			System.out.println("T=>"+T);
			
			if(minpainsum.sum>T.sum){
				minpainsum = T;
			}else if(minpainsum.sum==T.sum){
				if(minpainsum.compareTo(T)>0){
					minpainsum = T;
				}
				System.out.println("Same pain Sum!=>"+minpainsum);
			}
		}
		
		return cache[k][s][e]=minpainsum;
	}
	
//	public static long getMinPainSum(int s,int e,int k,int days){
//		System.out.println(s+"/"+e+"/"+k+"/"+days);
//		long minpainsum = INF;
//		if(e-s+1<k)
//			return INF;
//		
//		if(k==0){
//			return calPainAmout(s, e);
//		}
//			
//		for(int day=s;day<e+1;day++){
//			long T = getMinPainSum(s,day,0,day);
//			if((day+1<=e)&&(true)){
//				T = T+getMinPainSum(day+1, e, k-1,-1);
//			}
//			minpainsum = Math.min(minpainsum, T);
//		}
//		
//		return minpainsum;
//	}
	
	public static long calPainAmout(int s, int e){
		long pains = 0;
		long tired = 0;
		
		for(int n=s;n<e+1;n++){
			pains = pains + exc[n]*tired;
			tired = tired + exc[n];
		}
		
		return pains;
	}

}
