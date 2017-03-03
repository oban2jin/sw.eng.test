package secedu.off.s1c3;

import java.io.FileInputStream;
import java.util.Scanner;


class Pair{
	int s,e;
	public Pair(int s,int e){
		this.s=s;this.e=e;
	}
	public String toString(){
		return "["+s+":"+e+"]";
	}
}

class R{
	int minv=(int)1e9;
	Pair[] rlist;
	int cur=0;
	public R(){
		rlist = new Pair[10000+1];
	}
	public R(R o){
		rlist = new Pair[10000+1];
		copy(o);
	}
	public int add(Pair p){
		rlist[++cur]=p;
		return cur;
	}
	public int copy(R o){
		for(int i=1;i<o.cur+1;i++){
			this.rlist[i]=new Pair(o.rlist[i].s,o.rlist[i].e);  
		}
		this.cur=o.cur; 
		this.minv=o.minv;
		return cur;
	}
	public String toString(){
		String sb="{minv="+minv+"/"+"cur="+cur+" ";
		for(int n=1;n<cur+1;n++){
			sb=sb+rlist[n]+" ";
		}
		sb=sb+"}";
		return sb;
	}
}
public class Solution{
	public static int T;
	public static String N;
	public static int[] O,RO,P,RP;
	public static R[][][] cache;
	public static final int INF = (int)1e10;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/secedu/off/s1c3/sample.txt"));
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<T+1;t++){
			N=sc.next(); O = new int[N.length()]; RO=new int[N.length()]; P = new int[N.length()]; RP=new int[N.length()];
			cache = new R[N.length()+2][N.length()+2][10001];
			for(int i=0;i<N.length();i++){
				O[i]=N.charAt(i)=='(' ? 1:-1; RO[i]=N.charAt(i)=='(' ? -1:1; 
				if(i==0){ 
					P[i]=O[i]; RP[i]=RO[i];
				}else{
					P[i]=P[i-1]+O[i];
					RP[i]=RP[i-1]+RO[i];
				}
			}
			
			R r = new R();
			R ans=dp(0,N.length(),r,0);
			System.out.println("#"+t+" "+ans+" "+N);
		}
	}
	
	public static R dp(int i,int j,R r,int sum){
		System.out.println(i+":"+j+"/"+r+","+sum);
		R rslt=new R();
		
		if(r.cur>10) return rslt;
		
		if(i==N.length()){
			if(sum==0){
				r.minv=0;
				return r;
			}
			return rslt;
		}
		
		if(cache[i][j][sum]!=null){
			System.out.println("hit cache=>["+i+"]/["+j+"]=>"+cache[i][j][sum]);
			return cache[i][j][sum];
		}
		
		//(i,i)를 안뒤집어도 가능하면 => dp(i+1,j)호출
		if(sum+O[i]>=0){
			R tmp = dp(i+1,j,r,sum+O[i]);
			if(tmp.minv<rslt.minv){
				rslt.copy(tmp);
			}
		}
		
		for(int idx=i;idx<j;idx++){
			//R(i,idx)가능 여부확인 => 가능하면 dp(idx+1,j)호출
			boolean c = true; int tsum=sum;
			for(int ix=idx;ix>=i;ix--){
				tsum=tsum+RO[ix];
				if(tsum<0){
					c=false; break;
				}
			}
			if(c){
				R tmp = dp(idx+1,j,new R(),sum+RPSUM(i,idx));
				if(tmp.minv+1<rslt.minv){
					rslt.copy(tmp);
					rslt.add(new Pair(i, idx));
					rslt.minv = tmp.minv+1;
				}
			}
//			if(sum+RPSUM(i, idx)>=0){
//				if(sum==0&&RO[idx]<0) continue; 
//				R rr = new R(r); rr.add(new Pair(i, idx));
//				R tmp = dp(idx+1,j,rr,sum+RPSUM(i,idx));
//				if(tmp.minv<rslt.minv) rslt=tmp;
//			}
		}
		
		return cache[i][j][sum]=rslt;
	}
	
	public static int PSUM(int a, int b){
		if(a==0){
			return P[b];
		}else{
			return P[b]-P[a-1];
		}
	}
	public static int RPSUM(int a, int b){
		if(a==0){
			return RP[b];
		}else{
			return RP[b]-RP[a-1];
		}
	}
	public static int MIN(int a, int b){
		if(a<b){
			a^=b; b^=a; a^=b;
		}
		return b;
	}
}
