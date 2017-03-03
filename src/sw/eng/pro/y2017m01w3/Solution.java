package sw.eng.pro.y2017m01w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static int[] par;
	public static int[] rank;
	
	public static int T,N,M;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/pro/y2017m01w3/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			par = new int[N+1]; rank = new int[N+1];
			for(int n=1;n<N+1;n++) par[n]=n;
			for(int m=1;m<M+1;m++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
				System.out.println("join=>"+join(a,b));
			}
			
			for(int n=1;n<N+1;n++){
				System.out.println(n+"/"+par[n]+"/"+rank[n]);
			}
		}
	}
	
	public static int find(int n){
		if(n==par[n]) return par[n];
		return par[n]=find(par[n]);
	}
	
	public static int join(int a,int b){
		a = find(a); b=find(b);
		if(a==b) return a;
		
		if(rank[a]<rank[b]){
			a = a^b; b=b^a; a=a^b;
		}
		
		par[b] = a;
		rank[a]=rank[a]+1;
		
		return a;
	}

}
