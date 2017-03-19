package secedu.online.w2p2;

import java.io.FileInputStream;
import java.util.Scanner;


class BIT{
	int[] idx;
	public BIT(){
		
	}
}
public class Solution {
	public static int T,M;
	public static int MAXN=466;
	public static int MAX=700000;
	public static int[] dp;
	public static int[] MC;
	public static int[] MM;
//	public static 
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/secedu/online/w2p2/sample.txt"));
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<T+1;t++){
			M=sc.nextInt();
			PRE();
			ZMAX();
			int mm = 0; int mc=0;
			for(int m=1;m<M+1;m++){
				int tmp= GETMAX(m);
				if(mc<=tmp){
					mc=tmp; mm=m;
				}
			}
			System.out.println("#"+t+" "+mc+" "+mm+"/"+GETMAX(M));
		}
	}
	
	public static int GETMAX(int m){
		if(m==0||BS(m)==1) return m;
		
		if(m<MAX&&dp[m]!=-1) return dp[m];
		int r=0;
		int d=m/MC[BS(m)];
		r=d+GETMAX(m-d*MC[BS(m)]);
		return dp[m]=r;
	}
	public static void ZMAX(){
		dp=new int[MAX]; for(int n=0;n<dp.length;n++) dp[n]=-1;
		for(int n=1;n<MAX;n++){
			dp[n]=dp[n-MC[BS(n)]]+1;
		}
		
		MM=new int[MAXN]; MM[1]=7;
		for(int n=2;n<MAXN-1;n++){
			int s=MC[n]; int e=MC[n+1]-1;
			int mmax=0; int mcnt=0;
			int c=0;
			for(int m=s;m<=e;m=m+s){
				mcnt = Math.max(mcnt,c+MM[n-1]);
				c++;
			}
		}
	}
	public static void PRE(){
		MC = new int[MAXN+1];
		for(int n=1;n<MAXN;n++){
			MC[n]=n*n*n;
		}
	}
	public static int BS(int v){
		int hi,lo,mid=0;
		hi=MAXN;lo=1;
		while(lo<=hi){
			mid=(hi+lo)/2;
			if(v<MC[mid]){
				hi=mid-1;
			}else if(v>MC[mid]){
				lo=mid+1;
			}else{
				break;
			}
		}
		if(v<MC[mid]){
			mid--;
		}
		return mid;
	}

}
