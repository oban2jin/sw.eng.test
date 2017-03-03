package koitp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Range implements Comparable<Range>{
	int s,e;
	
	public Range(int s, int e){
		this.s=s;
		this.e=e;
	}
	
	@Override
	public int compareTo(Range o) {
		if(this.s==o.s){
			return o.e-this.e;
		}else{
			return this.s-o.s;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(s+"/"+e);
		return sb.toString();
	}
}

public class source {
	public static int N,Q;
	public static long[] inary;
	public static Range[] ask;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/koitp/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); Q = Integer.parseInt(st.nextToken());
		
		inary = new long[N+1];
		ask = new Range[Q+1];
		
		st = new StringTokenizer(br.readLine());
		for(int n=1;n<N+1;n++){
			inary[n] = Long.parseLong(st.nextToken());
		}
		
//		for(int n=1;n<inary.length;n++){
//			System.out.print(inary[n]+ " ");
//		}
//		System.out.println("");
		
		for(int q=1;q<Q+1;q++){
			st = new StringTokenizer(br.readLine());
			int s= Integer.parseInt(st.nextToken()); int e=Integer.parseInt(st.nextToken());
			ask[q] = new Range(s,e+s-1);
		}
		
		Arrays.sort(ask,1,ask.length);
//		
//		for(int n=1;n<ask.length;n++){
//			System.out.println(n+"=>"+ask[n]);
//		}
		getRangeSum();
		
		for(int n=1;n<inary.length;n++){
			System.out.print(inary[n]+ " ");
		}
	}
	
	public static void getRangeSum(){
		
		int sindex = 1; int eindex = 1;
		int svalue = ask[sindex].s; int evalue = ask[eindex].e;
		
		for(int n=1;n<ask.length;n++){
			if(ask[sindex].s!=ask[n].s){
//				System.out.println("getRangeSum(start)=>"+ask[sindex].s+"/"+ask[n].s);
//				System.out.println("change cnt=>"+(n-sindex));
				calAddRangeSum(ask[n-1].s, ask[n-1].e, n-sindex, 1);
				sindex = n ; eindex =n;
			}else if(ask[eindex].e!=ask[n].e){
//				System.out.println("getRangeSum(end)=>"+ask[eindex].e+"/"+ask[n].e);
//				System.out.println("change cnt=>"+(n-sindex));
				calAddRangeSum(ask[n].e+1,ask[eindex].e, n-sindex, ask[n].e+1-(Math.abs(1-ask[n].s)));
				eindex = n;
			}
		}
//		System.out.println("getRangeSum(last)=>"+ask[sindex].s+"/"+ask[eindex].e);
//		System.out.println("change cnt=>"+(Q-sindex+1));
		calAddRangeSum(ask[sindex].s, ask[eindex].e,Q-sindex+1, 1);
	}
	
	public static void calAddRangeSum(int s,int e,int cnt,int startno){
		
		for(int n=s;n<e+1;n++){
//			System.out.println("["+n+"]/"+(n+startno-s)*cnt);
			inary[n]=inary[n]+(n+startno-s)*cnt;
		}
		
	}

}
