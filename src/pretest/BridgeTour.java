package pretest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public class BridgeTour {

	public static int T,N,K,G;
	public static int StartPoint, EndPoint;
	public static int[] BridgeDistance;
	public static HashMap<Integer, Integer>[] BridgeGraph;
	public static int[] cache;
	public static boolean[] visited;
	public static int[] deleteCrossing;
	public static int MinPlusDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/pretest/BridgeTour.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for(int t=0;t<T;t++){
			String[] inP = br.readLine().split(" ");
			N = Integer.parseInt(inP[0]);
			K = Integer.parseInt(inP[1]);
			G = Integer.parseInt(inP[2]);

			StartPoint = 0; EndPoint = 2*N+1;
			BridgeDistance = new int[2*N+2];
			BridgeGraph = new HashMap[2*N+2];
			cache = new int[2*N+2];
			visited = new boolean[2*N+2];
			MinPlusDistance = Integer.MAX_VALUE;

			for(int i=0;i<cache.length;i++){
				cache[i]=-1;
				visited[i]=false;
			}

			String[] BrigePosN = br.readLine().split(" ");
			String[] BrigePosS = br.readLine().split(" ");

			BridgeDistance[StartPoint] = 0;
			for(int n=1;n<=N;n++){
				BridgeDistance[n]   = Integer.parseInt(BrigePosN[n-1]);
				BridgeDistance[N+n] = Integer.parseInt(BrigePosS[n-1]);
			}
			BridgeDistance[EndPoint] = G;

			createBridgeGraph();

			visited[StartPoint] = true;
			getMinTravelDistance(StartPoint);

			printCache();

			ArrayList minPath = new ArrayList();
			minPath.add(StartPoint);
			minPath = getMinTravelPath(minPath);

			System.out.println("#"+(t+1)+" "+solveProblem(minPath));


		}

	}//End-of-Main M

	public static int solveProblem(ArrayList minPath){
		System.out.println("*******************solveProblem()**********************************");
		System.out.println("minPath=>"+minPath);

		int bridgeCrossCnt=0;
		ArrayList<Integer> crossingBridgeNo = new ArrayList<Integer>();

		for(int i=0;i<minPath.size();i++){
			if(minPath.get(i)=="*"){
				bridgeCrossCnt++;
				int crosingbridge = ((Integer)minPath.get(i-1))*10000+((Integer)minPath.get(i+1));
				crossingBridgeNo.add(crosingbridge);
			}
		}

		if(bridgeCrossCnt<=K){
			return cache[StartPoint];
		}else{
			System.out.println("crossingBridgeNo=>"+crossingBridgeNo);

			deleteCrossing = new int[crossingBridgeNo.size()-1];

			for(int i=0;i<crossingBridgeNo.size()-1;i++){
				System.out.println(crossingBridgeNo.get(i)+","+crossingBridgeNo.get(i+1));
				int sbridge = crossingBridgeNo.get(i)/10000;
				int ebridge = crossingBridgeNo.get(i+1)%10000;
				System.out.println("sbridge:"+sbridge+","+"ebridge:"+ebridge);

				int originalDistance = BridgeDistance[ebridge]-BridgeDistance[sbridge];
				int minimumDistance = cache[sbridge] - cache[ebridge];
				System.out.println("originalDistance="+originalDistance+"/minimumDistance="+minimumDistance+"["+(originalDistance-minimumDistance)+"]");
				deleteCrossing[i] = (originalDistance-minimumDistance)*1000+i;
			}

			int gap = (bridgeCrossCnt-K+1)/2;
			System.out.println("K="+K+"/bridgeCrossCnt="+bridgeCrossCnt+"/Gap="+gap);

			ArrayList<Integer> deletecrossing = new ArrayList<Integer>();
//			Arrays.sort(deleteCrossing);

			return cache[StartPoint]+getMinimumRemoveCrossingBridgeBackUp(gap,0,deletecrossing);
		}
	}

	public static int getMinimumRemoveCrossingBridge(int removecnt,int plusdistance,ArrayList<Integer> deletecrossing){
		System.out.println("*********************getMinimumRemoveCrossingBridge()***************************************");
		System.out.println("removecnt="+removecnt+"/"+"plusdistance="+plusdistance+"/deletecrossing="+deletecrossing);

		int minpulsdistance = 0;
		int[] clone = deleteCrossing.clone();
		Arrays.sort(clone);
		
		for(int i=0;i<clone.length;i++){
			System.out.println("i="+i+"/clone[i]="+clone[i]);
		}
		
		for(int i=0;i<clone.length;i++){
			//minpulsdistance = minpulsdistance + deleteCrossing[i];
			int index = clone[i] % 1000;
			int distance = clone[i] / 1000;
			
			System.out.println("clone["+i+"]="+clone[i]);
			System.out.println("index="+index+"|contains(index)="+deletecrossing.contains(index)+"/contains(index-1)="+deletecrossing.contains(index-1)+"/contains(index+1)="+deletecrossing.contains(index+1));
			if((!deletecrossing.contains(index))&&(!deletecrossing.contains(index-1))&&(!deletecrossing.contains(index+1))){
				deletecrossing.add(index);
			}
			System.out.println("deletecrossing="+deletecrossing);
		}

		for(int i=0;i<removecnt;i++){
			minpulsdistance = minpulsdistance + deleteCrossing[deletecrossing.get(i)]/1000;
			System.out.println("deleteCrossing[deletecrossing.get(i)]:"+deleteCrossing[deletecrossing.get(i)]);
		}

		System.out.println("deletecrossing="+deletecrossing+"/minpulsdistance="+minpulsdistance);

		return minpulsdistance;
	}

	public static int getMinimumRemoveCrossingBridgeBackUp(int removecnt,int plusdistance,ArrayList<Integer> deletecrossing){
//		System.out.println("*********************getMinimumRemoveCrossingBridge()***************************************");
//		System.out.println("removecnt="+removecnt+"/"+"plusdistance="+plusdistance+"/deletecrossing="+deletecrossing);

		int minpulsdistance = Integer.MAX_VALUE;

		if(removecnt==0){
			MinPlusDistance = Math.min(MinPlusDistance, plusdistance);
			System.out.println("Reach the Basement!. MinPlusDistance="+MinPlusDistance+"deletecrossing="+deletecrossing);
			return plusdistance;			
		}
		
//		System.out.println("MinPlusDistance="+MinPlusDistance+"/plusdistance="+plusdistance);
		
		if(MinPlusDistance < plusdistance){
//			System.out.println("No Need..");
			return Integer.MAX_VALUE;
		}

		int lastBridge;
		if(deletecrossing.size()==0){
			lastBridge = 0;
		}else{
			lastBridge = deletecrossing.get(deletecrossing.size()-1);
		}
		
//		System.out.println("lastBridge=>"+lastBridge);
		
		for(int i=lastBridge;i<deleteCrossing.length;i++){
			int index = deleteCrossing[i] % 1000;
			int distance = deleteCrossing[i] / 1000;
			
//			System.out.println("index="+index+"|contains(index)="+deletecrossing.contains(index)+"/contains(index-1)="+deletecrossing.contains(index-1)+"/contains(index+1)="+deletecrossing.contains(index+1));
			if((!deletecrossing.contains(index))&&(!deletecrossing.contains(index-1))&&(!deletecrossing.contains(index+1))&&(lastBridge<=index)){
				deletecrossing.add(index);
				minpulsdistance= Math.min(minpulsdistance,getMinimumRemoveCrossingBridgeBackUp(removecnt-1,plusdistance+distance,deletecrossing));
				deletecrossing.remove(deletecrossing.size()-1);
			}
		}			

//		System.out.println("<&&&&minpulsdistance&&&&>="+minpulsdistance);

		return minpulsdistance;
	}

	public static ArrayList getMinTravelPath(ArrayList minPath){
		/*System.out.println("************getMinTravelPath(ArrayList<Integer> minPath)*****************");
		System.out.println("minPath=>"+minPath);*/

		int lastBridge = (Integer) minPath.get(minPath.size()-1);

		if(lastBridge==EndPoint){
			return minPath;
		}

		HashMap<Integer, Integer> nextBridges = BridgeGraph[lastBridge];
		Iterator iter = nextBridges.keySet().iterator();

		int nextBridge = Integer.MAX_VALUE;

		while(iter.hasNext()){
			nextBridge  = (Integer)iter.next();

			/*System.out.println("nextBridge:"+nextBridge);
			System.out.println("nextBridges.get(nextBridge):"+nextBridges.get(nextBridge));			
			System.out.println("cache[lastBridge]:"+cache[lastBridge]);
			System.out.println("cache[nextBridge]:"+cache[nextBridge]);*/

			if((cache[lastBridge]==cache[nextBridge]+nextBridges.get(nextBridge))){
				if(Math.abs(lastBridge-nextBridge)!=1){
					minPath.add("*");
				}
				minPath.add(nextBridge);
			}
		}

		return getMinTravelPath(minPath);

	}

	public static int getMinTravelDistance(int startPos){
		System.out.println("************getMinTravelDistance()********************");
		System.out.println("startPos=>"+ startPos);
		printVisted();

		int  mindistance = Integer.MAX_VALUE;

		if(startPos == EndPoint){
			System.out.println("Reach the EndPoint.");
			//EndPoint의 최소값이 계산에 사용
			cache[EndPoint]=0;
			return 0;
		}

		if(cache[startPos] != -1){
			System.out.println("Hit..cache=>"+startPos);
			return cache[startPos]; 
		}

		HashMap<Integer, Integer> nextBridges = BridgeGraph[startPos];
		System.out.println("nextBridge=>"+nextBridges);

		Iterator iter = nextBridges.keySet().iterator();
		while(iter.hasNext()){
			int nextBridge = (Integer)iter.next();

			if(visited[nextBridge] == false){
				visited[nextBridge] = true;
				mindistance = Math.min(mindistance, nextBridges.get(nextBridge) + getMinTravelDistance(nextBridge));
				visited[nextBridge] = false;
			}
		}

		/*		if(startPos>N){
			System.out.println("startPos[S"+(startPos-N)+"]/mindistance["+mindistance+"]");						
		}else{
			System.out.println("startPos[N"+startPos+"]/mindistance["+mindistance+"]");
		}*/

		cache[startPos] = mindistance;

		return mindistance;
	}

	public static void createBridgeGraph(){

		//EndPoint는 제외하기 위해 BridgeGraph.length-1까지만 루프
		for(int n=0;n<BridgeGraph.length-1;n++){
			HashMap<Integer, Integer> connected = new HashMap<Integer, Integer>();

			if(n==StartPoint){
				connected.put(n+1, BridgeDistance[n+1]-BridgeDistance[n]);
			}else if(n==EndPoint-1){
				//마지막 다리의 남쪽은 
				connected.put(n+1, BridgeDistance[EndPoint]-BridgeDistance[n]);
			}else if(n==N){
				//마지막 다리의 북쪽은
				connected.put(N+n+1, BridgeDistance[N+n+1]-BridgeDistance[N+n]);
			}else{
				if(n>N){
					//남쪽 - n+1 까지 올라가는 루트
					connected.put(n-N+1, BridgeDistance[n-N+1]-BridgeDistance[n-N]);
					connected.put(n+1, BridgeDistance[n+1]-BridgeDistance[n]);
				}else{
					//북쪽 - n+1 까지 내리가는 루트
					connected.put(N+n+1, BridgeDistance[n+N+1]-BridgeDistance[n+N]);
					connected.put(n+1, BridgeDistance[n+1]-BridgeDistance[n]);
				}
			}
			BridgeGraph[n] = connected;
		}

		System.out.println("****************createBridgeGraph()************************");
		for(int i=0;i<BridgeGraph.length;i++){
			System.out.println("BridgeGraph["+i+"]=>"+BridgeGraph[i]);
		}

	}

	public static void createBridgeGraphBackUp(){

		for(int n=0;n<BridgeGraph.length-1;n++){
			HashMap<Integer, Integer> connected = new HashMap<Integer, Integer>();
			if(n==StartPoint){
				connected.put(n+1, BridgeDistance[n+1]-BridgeDistance[n]);
			}else{
				//다리 건너는 루트 설정
				if(n>N){
					//남쪽 - n+1 까지 올라가는 루트
					//					connected.put(n-N, 0);
				}else{
					//북쪽 - n+1 까지 내리가는 루트
					//					connected.put(N+n, 0);
				}

				//도착지 루트 설정
				if(n==2*N){
					//남쪽 마지막 브릿지
					connected.put(n+1, BridgeDistance[EndPoint]-BridgeDistance[n]);
				}else if(n != N){
					connected.put(n+1, BridgeDistance[n+1]-BridgeDistance[n]);					
				}

			}
			BridgeGraph[n] = connected;
		}

		System.out.println("****************createBridgeGraph()************************");
		for(int i=0;i<BridgeGraph.length;i++){
			System.out.println("BridgeGraph["+i+"]=>"+BridgeGraph[i]);
		}

	}

	public static void printVisted(){
		System.out.println("-----------printVisted()---------------------");

		for(int i=0;i<visited.length;i++){
			if(visited[i]){
				if(i>N){
					if(i==EndPoint){
						System.out.println("visited[E]/"+visited[i]);
					}else{
						System.out.println("visited[S"+(i-N)+"]/"+visited[i]);						
					}
				}else{
					if(i==StartPoint){
						System.out.println("visited[S]/"+visited[i]);
					}else{
						System.out.println("visited[N"+(i)+"]/"+visited[i]);
					}
				}
			}
		}
	}

	public static void printCache(){
		System.out.println("-----------printCache()---------------------");

		for(int i=0;i<cache.length;i++){
			if(i>N){
				if(i==EndPoint){
					System.out.println("cache[E]/"+cache[i]);
				}else{
					System.out.println("cache[S"+(i-N)+"]/"+cache[i]);						
				}
			}else{
				if(i==StartPoint){
					System.out.println("cache[S]/"+cache[i]);
				}else{
					System.out.println("cache[N"+(i)+"]/"+cache[i]);
				}
			}
		}
	}

} //End-of-Class
