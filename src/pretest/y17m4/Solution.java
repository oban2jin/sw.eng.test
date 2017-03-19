package pretest.y17m4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class AR implements Comparable<AR>{
	int e,h;
	public AR(int e,int h){
		this.e=e; this.h=h;
	}
	public String toString(){
		return e+"/"+h;
	}
	@Override
	public int compareTo(AR o) {
		if(this.e==o.e){
			return this.h-o.h;
		}else{
			return this.e-o.e;
		}
	}
}
class RMQ{
	int MAXN;
	int[] idx;
	final int INF=(int)1e9;
	public void init(AR[] arr){
		int i=0;
		while(true){
			MAXN=1<<i++;
			if(MAXN>100000) break;
		}
		this.idx=new int[2*MAXN]; 
		for(int n=0;n<idx.length;n++)idx[n]=INF;
		
		int v=INF;
		for(int n=1;n<arr.length;n++){
			if(v!=arr[n].e){
				v=arr[n].e;
				update(v,n);
			}
		}
	}
	public void update(int i,int v){
		this.idx[MAXN+i]=v;
		int a=MAXN+i; a/=2;
		while(a!=0){
			idx[a]=Math.min(idx[2*a], idx[2*a+1]);
			a=a/2;
		}
	}
	public int qry(int a,int b){
		int ans=INF;
		int l=MAXN+a; int r=MAXN+b;
		while(l<=r){
			if(l%2==1) ans=Math.min(ans, idx[l++]);
			if(r%2==0) ans=Math.min(ans, idx[r--]);
			l=l/2;r=r/2;
		}
		return ans;
	}
}

public class Solution {
	public static final int MAXN = 100000;
	public static final int INF = (int)1e9;
	public static int T,N;
	public static AR[] O;
	public static boolean[] visit;
	public static int[] Z;
	public static RMQ rmq;
	public static int[] cache;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/pretest/y17m4/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		T=Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++){
			N=Integer.parseInt(br.readLine()); 
			O=new AR[N+1]; visit=new boolean[N+1]; rmq = new RMQ(); 
			Z = new int[MAXN+1]; Arrays.fill(Z, INF); cache=new int[MAXN+1]; Arrays.fill(cache, -1);
			for(int n=1;n<N+1;n++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				O[n]=new AR(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(O,1,O.length);
//			System.out.println("N=["+N+"]");
//			for(AR x:O) System.out.print(x+" ");
//			System.out.println("");
			
			rmq.init(O);
			
			long ans=DP(0);
			System.out.println("#"+t+" "+ans);
		}
		long endTime  = System.currentTimeMillis();
		System.out.println("Elapsed Time="+(endTime-startTime)/1000.0f);
	}
	
	public static int DP(int m){
//		System.out.println("DP Call m > ["+m+"]");
		int r=0;
		if(cache[m]!=-1)return cache[m];
		
		int inx =rmq.qry(m, MAXN); 
		if(inx==INF){
//			System.out.println("Base Case Return");
			return 0;
		}
		
		for(int n=inx;n<N+1;n++){
			if(!visit[n]){
//				System.out.println(O[n]);
				visit[n]=true;
				r=Math.max(r,O[n].h+DP(m+O[n].h));
				visit[n]=false;
			}
		}
		
		return cache[m]=r;
	}

}
