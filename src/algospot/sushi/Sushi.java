package algospot.sushi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;

public class Sushi {
	public static int C,N,M;
	public static int[] Price;
	public static int[] Value;
	public static TreeMap<Integer,Integer> cache = new TreeMap<Integer, Integer>();
	public static int UNIT = 100;
	public static int MaxPrice;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/sushi/Sushi.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine());
		for(int c=0;c<C;c++){
			String[] inP = br.readLine().split(" ");
			N = Integer.parseInt(inP[0]);
			M = Integer.parseInt(inP[1]);
			
			Price = new int[N];
			Value = new int[N];
			cache.clear();

			for(int n=0;n<N;n++){
				String[] inP2 = br.readLine().split(" ");
				Price[n] = Integer.parseInt(inP2[0]);
				Value[n] = Integer.parseInt(inP2[1]);
			}
			
			printInput();
			
			int[] Temp = Arrays.copyOf(Price, Price.length);
			Arrays.sort(Temp);
			MaxPrice = Temp[Temp.length-1];
			getMaxValues(MaxPrice);
			
			if(M>MaxPrice){
				int maxvalues = (M/MaxPrice)*cache.get(MaxPrice);
				System.out.println(maxvalues+getMaxValues(M%MaxPrice));
			}else{
				System.out.println(cache.get(M));
			}

			printCache();
		}
		
	}//End-of-Main
	
	public static int getMaxValues(int budgets){
		if(budgets==0)
			return 0;
		
		if(cache.containsKey(budgets)){
			return cache.get(budgets);
		}
		
		int maxvalues = 0;
		
		for(int n=0;n<N;n++){
			if(budgets>=Price[n]){
				maxvalues = Math.max(maxvalues, getMaxValues(budgets-Price[n])+Value[n]);		
			}
		}
		cache.put(budgets, maxvalues);
		return maxvalues;
	}
	
	public static void printInput(){
		System.out.println("N="+N+"/M="+M);
		for(int i=0;i<N;i++){
			System.out.println("Price:"+Price[i]+"/Value:"+Value[i]);
		}
	}
	
	public static void printCache(){
		System.out.println("**********Print Cache**************");
		Iterator iter = cache.keySet().iterator();
		
		while(iter.hasNext()){
			int budgetkey = (Integer)iter.next();
			System.out.println("budgets="+budgetkey+"/maxvalues="+cache.get(budgetkey));
		}
	}
}
