package secedu.online.w6p1;

import java.io.FileInputStream;
import java.util.Scanner;

class BST{
	int k; int v;
	BST lc,rc;
	public BST(int k,int v){
		this.k=k;this.v=v;
		this.lc=null;this.rc=null;
	}
	public int search(int k){
		BST r=this;
		while(true){
			if(k<r.k){
				if(r.lc!=null){
					r=r.lc;
				}else{
					return -1;
				}
			}else if(k>r.k){
				if(r.rc!=null){
					r=r.rc;
				}else{
					return -1;
				}
			}else if(k==r.k){
				return r.v;
			}
		}
	}
	public void add(BST n){
		BST r;
		if(n.k<this.k){
			for(r=this;r.lc!=null;r=r.lc);
			r.lc=n;
		}else{
			for(r=this;r.rc!=null;r=r.rc);
			r.rc=n;
		}
	}
}
class P{
	int i,j;
	public P(int i,int j){
		this.i=i;this.j=j;
	}
	public String toString(){
		return "("+i+","+j+")";
	}
}
public class Solution {
	public static int T,N,M;
	public static int[][]O;
	public static boolean[][]BITSET;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/secedu/online/w6p1/sample.txt"));
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<T+1;t++){
			BST bst = new BST(0,0);
			N=sc.nextInt();M=sc.nextInt();
			O=new int[N+1][M+1]; BITSET=new boolean[N+1][M+1];
			for(int n=1;n<N+1;n++) for(int m=1;m<M+1;m++){
				O[n][m]=sc.nextInt();
				if(bst.search(M*(n-1)+m)==-1){
					bst.add(new BST(M*(n-1)+m,O[n][m]));
				}else{
					System.out.println("Dup");
				}

			}
			int ans=DP(new P(1,1),new P(N,M));
			System.out.println(KEY(new P(1,1),new P(N,M)));
			System.out.println("#"+t+" "+ans);
		}
	}
	public static long KEY(P a, P b){
		for(int x=1;x<=N;x++) for(int y=1;y<=M;y++){
			BITSET[x][y]=false;
		}
		for(int x=a.i;x<=b.i;x++) for(int y=a.j;y<=b.j;y++){
			BITSET[x][y]=true;
		}
		long rslt=0; long exp=1;
		for(int i=1;i<=N;i++){
			long r=0;
			for(int j=1;j<=M;j++){
				if(BITSET[i][j]) r=r|(1<<(j-1));
			}
			System.out.println(r+"/"+exp);
			rslt=rslt+r*exp;
			exp=exp*N;
		}
		return rslt;
	}
	public static int DP(P a,P b){
		System.out.println(a.toString()+b.toString());
		int r=0;
		
		return r;
	}

}
