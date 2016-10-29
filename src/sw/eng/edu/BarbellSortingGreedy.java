package sw.eng.edu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;



/**
 * @author oban2jin
 * 10,000개 노드를 대상으로 Recursive Call을 이용한 Graph 탐색은 절대 1초대 수행시간에 답이 나오지 않는다.
 * 결국 탐욕적 알고리즘으로 문제를 해결해야 함.
 * 1)TreeMap / HashSet 사용법 숙지 
 */
public class BarbellSortingGreedy {
	
	public static int T,N;
	public static int[] NotSorted;
	public static int[] Sorted;
	public static HashMap<Integer,Integer> NotSortedIndex = new HashMap<Integer,Integer>();
	public static HashMap<Integer,Integer> SortedIndex = new HashMap<Integer,Integer>();
	public static TreeMap<Integer,HashSet> MinSwap = new TreeMap<Integer,HashSet>();;
	public static int MinWeightSum;
	public static BigInteger[] Factorial;
	public static HashMap<BigInteger, Integer> cache = new HashMap<BigInteger, Integer>();
	public static int INDEX_KEY = 10000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/edu/BarbellSorting.txt"));
//		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/edu/BackUpTest10000.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long startTime = System.currentTimeMillis();
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++){
			N = Integer.parseInt(br.readLine());
			
			MinWeightSum = Integer.MAX_VALUE;
			NotSorted = new int[N];
			Factorial = new BigInteger[N+1];
			NotSortedIndex.clear();
			MinSwap.clear();
			
			for(int n=0;n<N;n++){
				NotSorted[n] = Integer.parseInt(br.readLine());
				NotSortedIndex.put(NotSorted[n], n);
			}
			
			Sorted = (int[])NotSorted.clone();
			Arrays.sort(Sorted);
			preCal();
			
//			createMinWeight((N-getSamePositionCount(NotSorted)+1)/2,Sorted,0);
			
//			printCache();
			
			System.out.println("NotSorted=>"+printArray(NotSorted));
			System.out.println("Sorted   =>"+printArray(Sorted));
//			System.out.println("NotSortedIndex"+NotSortedIndex);
//			System.out.println("MinSwap"+MinSwap);
			
			System.out.println("#"+(t+1)+" "+ getMinWeightSum(0));
			System.out.println("NotSorted=>"+printArray(NotSorted));
			
			long endTime = System.currentTimeMillis();
			System.out.println("Time="+(endTime-startTime)/1000.0);
		}
		
	}// End-of-Main M
	
//	public static void createMinWeight(int depth,ArrayList<Integer> list,int weightSum){
//		System.out.println("***********************createMinWeight()*************************************");
//		System.out.println("depth="+depth+"/list="+list+"weightSum="+weightSum);
//		
//		int minWeitht = Integer.MAX_VALUE;
//		ArrayList<Integer> minlist = new ArrayList<Integer>();
//		
//		if(depth==0){
////			BigInteger stateNo = getStateNo(list);
////			cache.put(stateNo, weightSum);
////			System.out.println("cache create=>"+getStateNo(list)+"/"+weightSum);
//			return;
//		}
//		
//		for(int i=0;i<list.size();i++){
//			for(int j=i+1;j<list.size();j++){
//				int from = list.get(i);
//				int to = list.get(j);
//				if(minWeitht > (weightSum+from+to)){
//					minWeitht = weightSum+from+to;
//					System.out.println(i+"/"+j+":"+minWeitht);
//					minlist.add(i*100000+j);
//				}
//			}
//		}
//		
//		System.out.println("minlist="+minlist);
//		
//		for(int i=0;i<minlist.size();i++){
//			int fromi = minlist.get(i)/100000;
//		    int toi = minlist.get(i)%100000;
//		    
//		    int from = list.get(fromi);
//		    int to = list.get(toi);
//		    
//			list.set(fromi, to);
//			list.set(toi, from);
//			
//			BigInteger stateNo = getStateNo(list);
//			if(!cache.containsKey(stateNo)){
//				System.out.println("cache create=>"+stateNo+"/"+weightSum);
//				cache.put(stateNo, weightSum+from+to);
//				createMinWeight(depth-1,list,weightSum+from+to);					
//			}
//			list.set(fromi, from);
//			list.set(toi, to);
//		}
//	}
	
	public static void preCal(){
//		BigInteger factorialNo = BigInteger.ONE;
//		
//		for(int i=0;i<=N;i++){
//			if(i==0){
//				Factorial[i] = factorialNo;
//			}else{
//				Factorial[i] = Factorial[i-1].multiply(BigInteger.valueOf(i));
//			}
//		}
//		System.out.println("preCal Completed="+"preCal["+(Factorial.length-1)+"]:"+Factorial[Factorial.length-1]);
		for(int i=0;i<NotSorted.length;i++){
			int swapIndex = NotSortedIndex.get(Sorted[i]);
			int wsum = NotSorted[i]+NotSorted[swapIndex];
			
			if(i!=swapIndex){
				HashSet<Integer> swapindexpairs;
				if(MinSwap.containsKey(wsum)){
					swapindexpairs = MinSwap.get(wsum);
					swapindexpairs.add(Math.min(i,swapIndex)*INDEX_KEY+Math.max(i,swapIndex));
				}else{
					swapindexpairs = new HashSet<Integer>();
					swapindexpairs.add(Math.min(i,swapIndex)*INDEX_KEY+Math.max(i,swapIndex));
					MinSwap.put(wsum,swapindexpairs);
				}
			}
			
			SortedIndex.put(Sorted[i],i);
		}
	}
	
	public static BigInteger getStateNo(ArrayList<Integer> list){
		BigInteger stateNo = BigInteger.ZERO;
		
		for(int i=0;i<list.size();i++){
			int less = 0;
			for(int j=i+1;j<list.size();j++){
				if(list.get(i) > list.get(j)){
					less++;				
				}
			}
//			System.out.println("list.get(i)="+list.get(i)+"/"+less);
//			System.out.println("Factorial[N-1-i]:"+Factorial[N-i-1]);
			stateNo = stateNo.add(Factorial[N-i-1].multiply(BigInteger.valueOf(less)));
		}
		
//		System.out.println("list="+list+"/"+stateNo);
		
		return stateNo;
	}
	
//	public static int getSamePositionCount(ArrayList<Integer> notsorted){
//		int rslt = 0;
//		
//		for(int i=0;i<notsorted.size();i++){
//			if(notsorted.get(i) == Sorted.get(i)){
//				rslt++;
//			}
//		}
//		
//		System.out.println("getSamePositionCount="+rslt+"/notsorted=>"+notsorted);
//		return rslt;
//	}
	
	public static int getMinWeightSum(int weightsum){
//		System.out.println("******************getMinWeightSum()****************************************");
//		System.out.println("notsorted=>"+printArray(NotSorted));
		
		int minweightsum = 0;
		
		while(!MinSwap.isEmpty()){
			Iterator iter = MinSwap.keySet().iterator();
			int minswapkey = (Integer)iter.next();
			HashSet minswapList = (HashSet)MinSwap.get(minswapkey);
//			System.out.println("minswapkey="+minswapkey+"/minswapList=>"+minswapList);
			
			Iterator swapiter = minswapList.iterator();
			
			if(swapiter.hasNext()){
				int nextswap = (Integer)swapiter.next();
				int fromix = nextswap/INDEX_KEY;
				int toix = nextswap%INDEX_KEY;
				int from = NotSorted[fromix];
				int to = NotSorted[toix];
				int wsum=0;
				minswapList.remove(nextswap);
				if(minswapList.isEmpty()){
					MinSwap.remove(minswapkey);
				}
				
//				preCal();
				
				if(minswapkey==(from+to)){
					NotSorted[fromix] = to;
					NotSorted[toix] = from;
					wsum = from+to;
					
					NotSortedIndex.put(to, fromix);
					NotSortedIndex.put(from, toix);
					
					int newswap = Integer.MAX_VALUE;
					if(NotSorted[fromix]!=Sorted[fromix]){
						newswap = fromix;
					}
					if(NotSorted[toix]!=Sorted[toix]){
						newswap = toix;
					}
					
					if(newswap!=Integer.MAX_VALUE){
						int nwewsum=0;
						int swapIndex = NotSortedIndex.get(Sorted[newswap]);
						HashSet<Integer> swapindexpairs;
						nwewsum = NotSorted[newswap] + NotSorted[swapIndex];
						if(MinSwap.containsKey(nwewsum)){
							swapindexpairs = MinSwap.get(nwewsum);
							swapindexpairs.add(Math.min(newswap,swapIndex)*INDEX_KEY+Math.max(newswap,swapIndex));
						}else{
							swapindexpairs = new HashSet<Integer>();
							swapindexpairs.add(Math.min(newswap,swapIndex)*INDEX_KEY+Math.max(newswap,swapIndex));
							MinSwap.put(nwewsum,swapindexpairs);
						}
					
						swapIndex = SortedIndex.get(NotSorted[newswap]);
						nwewsum = NotSorted[newswap] + NotSorted[swapIndex];
						if(MinSwap.containsKey(nwewsum)){
							swapindexpairs = MinSwap.get(nwewsum);
							swapindexpairs.add(Math.min(newswap,swapIndex)*INDEX_KEY+Math.max(newswap,swapIndex));
						}else{
							swapindexpairs = new HashSet<Integer>();
							swapindexpairs.add(Math.min(newswap,swapIndex)*INDEX_KEY+Math.max(newswap,swapIndex));
							MinSwap.put(nwewsum,swapindexpairs);
						}
					}
				}else{
//					System.out.println("Change the sequence=>"+nextswap);
				}
				
//				System.out.println("NotSortedIndex=>"+NotSortedIndex);
//				System.out.println("NotSorted=>"+printArray(NotSorted));
//				System.out.println("Sorted   =>"+printArray(Sorted));
//				System.out.println("MinSwap=>"+MinSwap);
				
//				minweightsum = Math.min(minweightsum, getMinWeightSum(weightsum+wsum));
				minweightsum = minweightsum + wsum;
			}
		}

		return minweightsum;
	}
	
	public static void printCache(){
		Iterator iter = cache.keySet().iterator();
		while(iter.hasNext()){
			BigInteger key = (BigInteger)iter.next();
			System.out.println("cache["+key+"]="+cache.get(key));
		}
	}
	
	public static String printArray(int[] A){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<A.length;i++){
			sb.append(A[i]).append(" ");
		}
		return sb.toString();
	}
	
	public static String printHashMap(HashMap A){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
}// End-of-Class
