package sw.eng.edu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class BarbellSorting {
	
	public static int T,N;
	public static ArrayList<Integer> NotSorted = new ArrayList<Integer>();
	public static ArrayList<Integer> Sorted;
	public static int MinWeightSum;
	public static BigInteger[] Factorial;
	public static HashMap<BigInteger, Integer> cache = new HashMap<BigInteger, Integer>();
	public static ArrayList<BigInteger> visited = new ArrayList<BigInteger>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/edu/BarbellSorting.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++){
			N = Integer.parseInt(br.readLine());
			
			MinWeightSum = Integer.MAX_VALUE;
			NotSorted.clear();
			cache.clear();
			visited.clear();
			Factorial = new BigInteger[N+1];
			
			for(int n=0;n<N;n++){
				NotSorted.add(Integer.parseInt(br.readLine()));
			}
			
			preCal();
			Sorted = (ArrayList<Integer>)NotSorted.clone();
			Collections.sort(Sorted);
			
//			cache.put(getStateNo(Sorted), 0);
//			createMinWeight((N-getSamePositionCount(NotSorted)+1)/2,Sorted,0);
//			
//			printCache();
			
			//System.out.println("NotSorted=>"+NotSorted+"/"+getStateNo(NotSorted));
			//System.out.println("Sorted=>"+Sorted+"/"+getStateNo(Sorted));
			
			visited.add(getStateNo(NotSorted));
			System.out.println("#"+(t+1)+" "+ getMinWeightSum(NotSorted,0,0));
			
		}
		
	}// End-of-Main M
	
	public static void createMinWeight(int depth,ArrayList<Integer> list,int weightSum){
		//System.out.println("***********************createMinWeight()*************************************");
		//System.out.println("depth="+depth+"/list="+list+"weightSum="+weightSum);
		
		int minWeitht = Integer.MAX_VALUE;
		ArrayList<Integer> minlist = new ArrayList<Integer>();
		
		if(depth==0){
//			BigInteger stateNo = getStateNo(list);
//			cache.put(stateNo, weightSum);
//			//System.out.println("cache create=>"+getStateNo(list)+"/"+weightSum);
			return;
		}
		
		for(int i=0;i<list.size();i++){
			for(int j=i+1;j<list.size();j++){
				int from = list.get(i);
				int to = list.get(j);
				if(minWeitht > (weightSum+from+to)){
					minWeitht = weightSum+from+to;
					//System.out.println(i+"/"+j+":"+minWeitht);
					minlist.add(i*100000+j);
				}
			}
		}
		
		//System.out.println("minlist="+minlist);
		
		for(int i=0;i<minlist.size();i++){
			int fromi = minlist.get(i)/100000;
		    int toi = minlist.get(i)%100000;
		    
		    int from = list.get(fromi);
		    int to = list.get(toi);
		    
			list.set(fromi, to);
			list.set(toi, from);
			
			BigInteger stateNo = getStateNo(list);
			if(!cache.containsKey(stateNo)){
				//System.out.println("cache create=>"+stateNo+"/"+weightSum);
				cache.put(stateNo, weightSum+from+to);
				createMinWeight(depth-1,list,weightSum+from+to);					
			}
			list.set(fromi, from);
			list.set(toi, to);
		}
	}
	
	public static void preCal(){
		BigInteger factorialNo = BigInteger.ONE;
		
		for(int i=0;i<=N;i++){
			if(i==0){
				Factorial[i] = factorialNo;
			}else{
				Factorial[i] = Factorial[i-1].multiply(BigInteger.valueOf(i));
			}
		}
		//System.out.println("preCal Completed="+"preCal["+(Factorial.length-1)+"]:"+Factorial[Factorial.length-1]);
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
//			//System.out.println("list.get(i)="+list.get(i)+"/"+less);
//			//System.out.println("Factorial[N-1-i]:"+Factorial[N-i-1]);
			stateNo = stateNo.add(Factorial[N-i-1].multiply(BigInteger.valueOf(less)));
		}
		
//		//System.out.println("list="+list+"/"+stateNo);
		
		return stateNo;
	}
	
	public static int getSamePositionCount(ArrayList<Integer> notsorted){
		int rslt = 0;
		
		for(int i=0;i<notsorted.size();i++){
			if(notsorted.get(i) == Sorted.get(i)){
				rslt++;
			}
		}
		
		//System.out.println("getSamePositionCount="+rslt+"/notsorted=>"+notsorted);
		return rslt;
	}
	
	public static int getMinWeightSum(ArrayList<Integer> notsorted,int depth,int T){
		//System.out.println("******************getMinWeightSum()****************************************");
		for(int n=0;n<depth;n++){
			//System.out.print("->");
		}
		BigInteger stateno = getStateNo(notsorted);
		//System.out.println("stateno:"+stateno+"/T="+T+"/notsorted=>"+notsorted);
		
		int minweightsum = Integer.MAX_VALUE - 10000;

		if(notsorted.equals(Sorted)){
			MinWeightSum = Math.min(MinWeightSum, T);
			//System.out.println("Reach the Basement. MinWeightSum=>"+MinWeightSum);
			//System.out.println("Reach the Basement. T=>"+T);
			return 0;
		}
		
		if(cache.containsKey(stateno)){
			//System.out.println("Hit the cache.:"+cache.get(stateno));
			return cache.get(stateno);
		}
		
		for(int i=0;i<notsorted.size();i++){
			for(int j=i+1;j<notsorted.size();j++){
				int from = notsorted.get(i);
				int to = notsorted.get(j);
				
				notsorted.set(i, to);
				notsorted.set(j, from);
				BigInteger nextStateNo =  getStateNo(notsorted);
				
				if(T>=MinWeightSum){
					//System.out.println("No Need To Call.:"+notsorted+"/"+MinWeightSum);
				}else{
					if(!visited.contains(nextStateNo)){
						visited.add(nextStateNo);
						minweightsum = Math.min(minweightsum,from+to+getMinWeightSum(notsorted,depth+1,T+from+to));
						visited.remove(nextStateNo);
					}
				}

				notsorted.set(i, from);
				notsorted.set(j, to);
			}
			//notsorted에서 한번 위치 교환해서, Sorted와 더 가까워 지는 경우에만 getMinWeightSum을 재귀호출한다.
		}
		
		cache.put(stateno, minweightsum);
		//System.out.println("new cache put:"+stateno+"/"+minweightsum);
		
		return minweightsum;
	}
	
	public static void printCache(){
		Iterator iter = cache.keySet().iterator();
		while(iter.hasNext()){
			BigInteger key = (BigInteger)iter.next();
			//System.out.println("cache["+key+"]="+cache.get(key));
		}
	}
}// End-of-Class
