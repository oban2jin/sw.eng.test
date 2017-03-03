package basic.sparsetable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SparseTable {
	
	public static int N,MAXN;
	public static int[][] sp;
	public static int[] depth;
	public static ArrayList[] G;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/basic/sparsetable/sparsetable.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		depth = new int[N+1];
		G = new ArrayList[N+1]; for(int n=1;n<N+1;n++)G[n] = new ArrayList<Integer>();
		
		int i=1;
		while(true){
			if(N < 1<<i){
				MAXN=i;
				break;
			}
			i++;
		}
		
		sp = new int[N+1][MAXN];
		for(int n=1;n<N+1;n++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); int p = Integer.parseInt(st.nextToken());
			sp[c][0] = p; G[p].add(c);
		}
		sparsetable();
		caldepth(N);
		int c=LCA(1,4);
		System.out.println(c);
	}
	
	public static int LCA(int a,int b){
		if(depth[a]<depth[b]){
			a=a^b; b=b^a; a=a^b; 
		}
		
		for(int i=MAXN-1;i>=0;i--){
			if(((1<<i)&(depth[a]-depth[b]))!=0){
				a=sp[a][i];
			}
		}
		
		if(a==b) return a;
		
		for(int i=MAXN-1;i>=0;i--){
			if(sp[a][i]!=sp[b][i]){
				 a=sp[a][i]; b=sp[b][i];
			}
		}
		
		return sp[a][0];
	}
	public static void caldepth(int root){
		int d=0;
		boolean[] visited = new boolean[N+1]; 
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(root); visited[N]=true; depth[root]=0;
		
		while(!Q.isEmpty()){
			int here = Q.poll();
			ArrayList next = G[here];
			for(int n=0;n<next.size();n++){
				int there = (Integer)next.get(n);
				if(!visited[there]){
					Q.add(there); depth[there] = depth[here]+1;
				}
			}
		}
		
	}
	
	public static void sparsetable(){
		for(int n=1;n<N+1;n++){
			for(int i=1;i<MAXN;i++){
				sp[n][i]=sp[sp[n][i-1]][i-1];
			}
		}
	}
}
