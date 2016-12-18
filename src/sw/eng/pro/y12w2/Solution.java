package sw.eng.pro.y12w2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static int T,N,K;
	public static boolean[] visited;
	public static int[] restrict;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/pro/y12w2/y12w2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1];
			restrict = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<N+1;n++){
				restrict[n] = Integer.parseInt(st.nextToken());
			}
			
			long rslt = 1;
			for(int n=1;n<N+1;n++){
				if(!visited[n]){
					rslt = rslt*doBFS(n);
				}
			}
			
			System.out.println("#"+(t+1)+" "+rslt);
		}
		
	}
	
	public static long doBFS(int start){
		System.out.println("*************************************");
		Queue<Integer> Q = new LinkedList<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		long rslt = 1;
		Q.add(start);
		path.add(start);
		visited[start] = true;
		int lastnode = Integer.MAX_VALUE;
		int circlesize = Integer.MAX_VALUE;
		int notcirclesize = Integer.MAX_VALUE;
		
		while(!Q.isEmpty()){
			int here = Q.poll();
			int next = restrict[here];
			
			if(!visited[next]){
				visited[next]= true;
				path.add(next);
				Q.add(next);
			}else{
				lastnode = next;
			}
		}
		
		
		System.out.println(path);
		System.out.println("lastnode="+lastnode);
		
		if(path.indexOf(lastnode)>-1){
			System.out.println("circle="+(path.size()-path.indexOf(lastnode)));
			System.out.println("not circle="+(path.indexOf(lastnode)));
			circlesize = path.size()-path.indexOf(lastnode);
			notcirclesize = path.indexOf(lastnode);
		}else{
			System.out.println("not circle="+(path.size()+1)*(K-1));
			notcirclesize = path.size()+1;
		}
		
		return rslt;
	}

}
