package basic.binarysearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class RMQ{
	int[] idx;
	int MAXN;
	public RMQ(int size){
		int i=0;
		while(true){
			MAXN=1<<i++;
			if(MAXN > size) break;
		}
		idx=new int[MAXN*2];
	}
	public void init(int[] arr){
		Arrays.fill(idx, 0);
		for(int n=0;n<arr.length;n++) update(arr[n],n);
	}
	public void update(int i,int v){
		int a=MAXN+i; idx[a]=v;
		a=a/2;
		while(a!=0){
			idx[a]=Math.max(idx[2*a], idx[2*a+1]);
			a=a/2;
		}
	}
	public int qry(int a,int b){
		int ans=0;
		int ll=MAXN+a; int rr=MAXN+b;
		while(ll<=rr){
			if(ll%2==1)ans=Math.max(ans, idx[ll++]);
			if(rr%2==0)ans=Math.max(ans, idx[rr--]);
			ll=ll/2; rr=rr/2;
		}
		return ans;
	}
}
public class Solution {
	public static int MAXN=11;
	public static int MAX=1000;
	public static int T;
	public static int[] O;
	public static RMQ rmq;
	public static void main(String[] args)throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/basic/binarysearch/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		O=new int[MAXN];
		for(int n=0;n<MAXN;n++){
			O[n]=n*n*n;
		}
		for(int x:O) System.out.print(x+" ");
		System.out.println("");
		
		rmq = new RMQ(MAX);
		rmq.init(O);
		
		System.out.println("rqm.qry=>"+rmq.qry(0,8));
	}

}
