package sw.eng.edu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BarbellSortingIndexFunction {

	public static int T,N;
	public static ArrayList<Integer> Sorted,NotSorted;

	public static ArrayList<HashMap<Integer, Integer>> Graph;
	public static HashMap<ArrayList, Integer> State;
	public static int  SortedState;
	public static boolean[] Visited;

	public static final	long First = 1000000;

	public static BigInteger[] Factorial;
	public static HashMap<Integer, ArrayList<Long>> WeightOrder;
	//최소값 상태가 여러개 있을수 있음을 고려.
	public static HashMap<ArrayList<Integer>,Integer> MinState = new HashMap<ArrayList<Integer>,Integer>();
	public static int MinWeight;
	public static HashMap<BigInteger, Integer> MinW = new HashMap<BigInteger, Integer>();
	public static HashMap<BigInteger,Integer> cache;
	public static BigInteger NotSortedNo;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/edu/BarbellSorting.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		NotSorted = new ArrayList<Integer>();
		Graph = new ArrayList<HashMap<Integer, Integer>> ();
		State = new HashMap<ArrayList, Integer>(); 
		WeightOrder = new HashMap<Integer, ArrayList<Long>>();
		cache  = new HashMap<BigInteger,Integer>();

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
			doPreCondition();

			NotSortedNo = getIndex(NotSorted);
			System.out.println("End of doPreCondition=>"+NotSortedNo);
			MinW.put(getIndex(Sorted), 0);
			System.out.println("MinPath="+getCrticalMinWeightRecursive(0,Sorted));


			//			createGraph();
			//			SortedState = State.get(Sorted);
			//			Visited = new boolean[State.size()];
			//			cache = new int[State.size()];

			Iterator x = MinState.keySet().iterator();
			while(x.hasNext()){
				ArrayList<Integer> xx  = (ArrayList<Integer>)x.next();
				System.out.println(MinState.get(xx)+"/"+xx);
			}

			//			Visited[0] = true;
			//			System.out.println("# "+t+" "+sortDFS(0));

			//			for(int c : cache ){
			//				System.out.println(c+ " ");
			//			}
		}

	}

	public static void doPreCondition(){

		Factorial = new BigInteger[NotSorted.size()+1];
		Factorial[0] = BigInteger.ONE;
		for(int i=1;i<NotSorted.size()+1;i++){
			if(i==1){
				Factorial[i] = BigInteger.ONE;
			}else{
				Factorial[i] = Factorial[i-1].multiply(BigInteger.valueOf(i));
			}
		}

		//		for(int i=0;i<NotSorted.size();i++){
		//			for(int j=i+1;j<NotSorted.size();j++){
		//
		//				int w = NotSorted.get(i) + NotSorted.get(j);	
		//				if(!WeightOrder.containsKey(w)){
		////					ArrayList<Long> wsum = new ArrayList<Long>();
		////					WeightOrder.put(w, wsum);
		//				}
		//				ArrayList<Long> wsum = WeightOrder.get(w);
		//				//wsum.add(Math.min(NotSorted.get(i),NotSorted.get(j))*First+Math.max(NotSorted.get(i), NotSorted.get(j)));
		//			}
		//		}
		//
		//		System.out.println("WeightOrder="+WeightOrder);

	}

	public static BigInteger getIndex(ArrayList<Integer> state){
		//		System.out.println("state=>"+state);
		BigInteger indexvalue = BigInteger.valueOf(0);

		for(int i=0;i<state.size();i++){
			int less = 0;
			for(int j=i+1;j<state.size();j++){
				if(state.get(i)>state.get(j)){
					less++;
				}
			}
			indexvalue = indexvalue.add(Factorial[state.size()-1-i].multiply(BigInteger.valueOf(less)));
		}

		return indexvalue;
	}

	public static int getCrticalMinWeightRecursive(int weightsum,ArrayList<Integer> miniPath){
		System.out.println("***********************************************");
		System.out.println("weightsum="+weightsum+"/miniPath"+miniPath+"/"+N+","+(N/2)+","+(N%2));

		int minW = Integer.MAX_VALUE;
		BigInteger stateNo = getIndex(miniPath);

		if(NotSortedNo.equals(stateNo)){
			//기저 조건을 (2/N + 2%N)+1
			//MinPath에 저장하고,
			System.out.println("base case=>"+miniPath+"/"+weightsum);
			return weightsum;
		}

		if(cache.containsKey(stateNo)){
			System.out.println("Hit cache=>"+stateNo);
			return cache.get(stateNo)+weightsum;
		}

		for(int i=0;i<NotSorted.size();i++){
			for(int j=0;j<NotSorted.size();j++){
				
				if(NotSorted.get(i)!=miniPath.get(j)){
					int firstContents = NotSorted.get(i);
					int firstIdx = j;
					int xx = miniPath.get(j);
					int secondIdx = miniPath.indexOf(firstContents);
					int secondContents = miniPath.get(secondIdx);
					
					System.out.println("firstIdx="+firstIdx+"/firstContents="+firstContents);
					System.out.println("secondIdx="+secondIdx+"/secondContents="+secondContents);
					
					miniPath.set(firstIdx, secondContents);
					miniPath.set(secondIdx, xx);
					
					int w= xx + secondContents;
					
					if(!MinW.containsKey(getIndex(miniPath))){
						if(minW>w){
							MinW.put(getIndex(miniPath), w);
							minW = Math.min(minW, getCrticalMinWeightRecursive(weightsum+w,miniPath));
							MinW.remove(getIndex(miniPath));
						}
					}
					
					miniPath.set(firstIdx, xx);
					miniPath.set(secondIdx, secondContents);
							
				}
			}
		}
		
		cache.put(stateNo,minW);
		return minW;
	}

	public static int createCrticalMinWeightRecursive(int depth,int weightsum,ArrayList<Integer> miniPath){
		//		System.out.println("***********************************************");
		//		System.out.println("depth="+depth+"/weightsum="+weightsum+"/miniPath"+miniPath+"/"+N+","+(N/2)+","+(N%2));

		int minW = Integer.MAX_VALUE;


		if(depth==(N/2+N%2)-1){
			//기저 조건을 (2/N + 2%N)+1
			//MinPath에 저장하고,
			System.out.println("base case=>"+miniPath+"/"+weightsum+"/"+(N/2+N%2-1));
			MinState.put((ArrayList<Integer>)miniPath.clone(), weightsum);
			return weightsum;
		}

		boolean find = false;
		Iterator iter = WeightOrder.keySet().iterator();
		while(iter.hasNext()){
			int key = (Integer)iter.next();
			ArrayList<Long> weightorder = WeightOrder.get(key);

			for(int i=0;i<weightorder.size();i++){
				int first = (int) (weightorder.get(i)/First);
				int second = (int)(weightorder.get(i)%First);

				int firstindex = miniPath.indexOf(first);
				int secondindex = miniPath.indexOf(second);

				miniPath.set(firstindex, second);
				miniPath.set(secondindex, first);

				if(!MinW.containsKey(getIndex(miniPath))){
					find = true;
					MinW.put(getIndex(miniPath), first+second);
					minW = Math.min(minW, createCrticalMinWeightRecursive(depth+1,weightsum+first+second,miniPath));
					MinW.remove(getIndex(miniPath));
				}else{
					//					System.out.println("Already Exist=>"+miniPath);
				}

				//원복
				miniPath.set(firstindex, first);
				miniPath.set(secondindex, second);
			}

			if(find)
				break;

		}

		return minW;
	}

	public static void createCritialMinWeight(int depth){
		ArrayList<Integer> bMinSate = NotSorted;

		for(int cnt=0;cnt<depth;cnt++){
			Iterator iter = WeightOrder.keySet().iterator();
			while(iter.hasNext()){
				//합의 최소 바벨을 돌아 가면서 바꿔보고, 이미 생성한적이 없으면 해당 뎁스의 최소 무게로 생성하고 while 루프를 빠진다.
				//무게합 구성 조합이 여러개일 가능성 있음. 반복 조건에 포함 시켜야 함.
				int key = (Integer)iter.next();
				ArrayList<Long> weightorder = WeightOrder.get(key);

				for(int i=0;i<weightorder.size();i++){
					int first = (int) (weightorder.get(i)/First);
					int second = (int)(weightorder.get(i)%First);

					int firstindex = bMinSate.indexOf(first);
					int secondindex = bMinSate.indexOf(second);

					bMinSate.set(firstindex, second);
					bMinSate.set(secondindex, first);

					if(!MinW.containsKey(bMinSate)){
						MinW.put(getIndex(bMinSate), first+second);
					}

					//원복
					bMinSate.set(firstindex, first);
					bMinSate.set(secondindex, second);
				}

			} // End - While - WeightOrder
		}// End - for : depth

	}


	public static void createGraph(){
		int stateNumber = 0;
		State.put(NotSorted, stateNumber);

		Queue<ArrayList> Q = new LinkedList<ArrayList>();
		Q.add(NotSorted);

		while(!Q.isEmpty()){

			ArrayList<Integer> beforestate =  Q.poll();

			HashMap connected = new HashMap<Integer, Integer>();
			Graph.add(connected);

			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){
					ArrayList<Integer> nextState = (ArrayList)beforestate.clone();

					nextState.set(i, beforestate.get(j));
					nextState.set(j, beforestate.get(i));

					if(!State.containsKey(nextState)){
						Q.add(nextState);
						System.out.println("beofeState="+beforestate+"nextState="+nextState);
						stateNumber++;
						State.put(nextState, stateNumber);
						connected.put(stateNumber, beforestate.get(i)+beforestate.get(j));
					}
				}
			}
		}

		System.out.println(State);
		System.out.println(Graph);
	}

	public static int sortDFS(int startNode){
		int minweight = Integer.MAX_VALUE-100000*10000;
		//		
		//		if(cache[startNode]!=-1){
		//			return cache[startNode];
		//		}

		if(startNode == SortedState){
			return 0;
		}

		HashMap<Integer, Integer> nextNodes = Graph.get(startNode);

		Iterator<Integer> iter = nextNodes.keySet().iterator();

		while(iter.hasNext()){
			int nextNode  = (Integer) iter.next();
			if(!Visited[nextNode]){
				Visited[nextNode] = true;
				minweight = Math.min(minweight, sortDFS(nextNode) + nextNodes.get(nextNode)) ;
			}
		}

		//		cache[startNode] = minweight;

		return  minweight;
	}

}
