package algospot.network;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class NetworkFlow {
	public static int[][] capacity;
	public static int[][] flow;
	public static int[][] remain;
	public static int[] parent;
	public static int N,M;
	
	public static int START,END;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/network/networkflow.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());		
		capacity = new int[N][N];
		flow = new int[N][N];
		remain = new int[N][N];
		parent = new int[N];
		
		for(int m=0;m<M;m++){
			st = new StringTokenizer(br.readLine());
			capacity[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=Integer.parseInt(st.nextToken());
		}
		
		
		START = 0;
		END = N-1;
		
		System.out.println(getMaxNetworkFlow(START,END));
		
	}
	
	public static int getMaxNetworkFlow(int s,int e){
		int _MaxFlowAmt=0;
		
		
		while(true){
			Arrays.fill(parent, -1);
			for(int[] X : remain){
				Arrays.fill(X, 0);
			}
			
			//증가경로를 찾는다. BFS
			Queue<Integer> Q = new LinkedList<Integer>();
			parent[START]=START;
			Q.add(START);
			while(!Q.isEmpty()){
				int here = Q.poll();
				int[] nexts = capacity[here];
				
				for(int there=0;there<nexts.length;there++){
					if((capacity[here][there] > flow[here][there])&&(parent[there]==-1)){
						//찾은 가능유량을 저장한다.?
						remain[here][there]=capacity[here][there]-flow[here][there];
						parent[there] = here;
						Q.add(there);
					}
				}
			}
			
			if(parent[END]==-1)
				break;
			//최소유량을 찾는다.
			int amt = Integer.MAX_VALUE;
			for(int node=END;node!=START;node=parent[node]){
				amt = Math.min(amt, remain[parent[node]][node]);
			}
			
			//유량을 흘린다.
			for(int node=END;node!=START;node=parent[node]){
				flow[parent[node]][node] = flow[parent[node]][node] + amt;
				flow[node][parent[node]] = flow[node][parent[node]] - amt; 
			}
			
			_MaxFlowAmt=_MaxFlowAmt+amt;
			
		}

		return _MaxFlowAmt;
	}

}
