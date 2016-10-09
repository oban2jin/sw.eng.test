package sw.eng.edu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class BarbellSorting {
	
	public static int T,N;
	public static ArrayList<Integer> Sorted,NotSorted;
	public static ArrayList<HashMap<Integer, Integer>> Graph;
	public static HashMap<ArrayList, Integer> State;
	public static int  SortedState;
	public static boolean[] Visited;
	public static int[] cache;

	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/edu/BarbellSorting.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		NotSorted = new ArrayList<Integer>();
		Graph = new ArrayList<HashMap<Integer, Integer>> ();
		State = new HashMap<ArrayList, Integer>(); 
		
		for(int t=1;t<=T;t++){
			N = Integer.parseInt(br.readLine());
			NotSorted.clear();
			Graph.clear();
			State.clear();
			
			for(int n=0;n<N;n++){
				NotSorted.add(Integer.parseInt(br.readLine()));
			}
			
			Sorted = (ArrayList)NotSorted.clone();
			Collections.sort(Sorted);
			
			createGraph();
			SortedState = State.get(Sorted);
			Visited = new boolean[State.size()];
			cache = new int[State.size()];
			
			for(int x=0;x<State.size();x++){
				cache[x] = -1;
			}
			
			Visited[0] = true;
			System.out.println("# "+t+" "+sortDFS(0));
			
			for(int c : cache ){
				System.out.println(c+ " ");
			}
		}
		
	}
	
	public static void createGraph(){
		int stateNumber = 0;
		State.put(NotSorted, stateNumber);
		
		ArrayList Q = new ArrayList();
		Q.add(NotSorted);
		
		while(!Q.isEmpty()){
			ArrayList<Integer> beofeState =  (ArrayList<Integer>) Q.get(0);
			Q.remove(0);
			
			HashMap connected = new HashMap<Integer, Integer>();
			Graph.add(connected);
			
			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){
					ArrayList<Integer> nextState = (ArrayList)beofeState.clone();
					
					nextState.set(i, beofeState.get(j));
					nextState.set(j, beofeState.get(i));
					
					if(!State.containsKey(nextState)){
						Q.add(nextState);
						System.out.println("beofeState="+beofeState+"nextState="+nextState);
						stateNumber++;
						State.put(nextState, stateNumber);
						connected.put(stateNumber, beofeState.get(i)+beofeState.get(j));
					}
				}
			}
		}
		
		System.out.println(State);
		System.out.println(Graph);
	}
	
	public static int sortDFS(int startNode){
		int minweight = Integer.MAX_VALUE-100000*10000;
		
		if(cache[startNode]!=-1){
			return cache[startNode];
		}
		
		if(startNode == SortedState){
			return 0;
		}
		
		HashMap<Integer, Integer> nextNodes = Graph.get(startNode);
		
		Iterator iter = nextNodes.keySet().iterator();
		
		while(iter.hasNext()){
			int nextNode  = (Integer) iter.next();
			if(!Visited[nextNode]){
				Visited[nextNode] = true;
				minweight = Math.min(minweight, sortDFS(nextNode) + nextNodes.get(nextNode)) ;
			}
		}
		
		cache[startNode] = minweight;
		
		return  minweight;
	}
	
}
