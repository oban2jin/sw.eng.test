package algospot.packing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Packing {
	
	public static int C,N,W;
	public static String[] Items;
	public static int[] Weight;
	public static int[] Values;
	public static int[][] cache;
	public static ArrayList<String> Pack = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/packing/Packing.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine());
		
		for(int c=0;c<C;c++){
			String[] inP = br.readLine().split(" ");
			N = Integer.parseInt(inP[0]);
			W = Integer.parseInt(inP[1]);
			
			Pack.clear();
			Items = new String[N];
			Weight = new int[N];
			Values = new int[N];
			cache = new int[N][W+1];
			for(int n=0;n<N;n++){
				for(int w=0;w<W+1;w++){
					cache[n][w]=-1;
				}
			}
			
			for(int n=0;n<N;n++){
				inP = br.readLine().split(" ");
				Items[n] = inP[0];
				Weight[n] = Integer.parseInt(inP[1]);
				Values[n] = Integer.parseInt(inP[2]);
			}
			int maxvalues = getMaxValues(0, W);
//			getItemCount(0,W);
			printCache();
		}
		
	}//End-of-Main
	
	public static void getItemCount(int itemidx,int remainweight){
		if(itemidx==N){
			return;
		}
		
		if(cache[itemidx][remainweight] == cache[itemidx+1][remainweight-Weight[itemidx]]+Values[itemidx]){
			Pack.add(Items[itemidx]);
		}
		
		getItemCount(itemidx+1,W);
		
	}
	
	public static int getMaxValues(int itemidx, int remainweight){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>getMaxValues()");
		System.out.println("itemidx:"+itemidx+"/remainweight="+remainweight);

		if(itemidx==N){
			//기저사례 
			return 0;
		}
		
		if(cache[itemidx][remainweight]!=-1){
			return cache[itemidx][remainweight];
		}
		
		cache[itemidx][remainweight] = getMaxValues(itemidx+1, remainweight);
		if(Weight[itemidx] <= remainweight){
			cache[itemidx][remainweight] =  Math.max(getMaxValues(itemidx+1, remainweight-Weight[itemidx])+Values[itemidx], cache[itemidx][remainweight]);
		}
		
		System.out.println("itemidx:"+itemidx+"/remainweight="+remainweight);
		System.out.println("getMaxValues()<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		return cache[itemidx][remainweight];
	}
	
//	public static int getMaxValues2(int itemidx, int remainweight){
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>getMaxValues()");
//		System.out.println("itemidx:"+itemidx+"/remainweight="+remainweight);
//		
//		if(itemidx==0){
//			//기저사례 
//			if(Weight[itemidx] <= remainweight){
//				cache[itemidx][remainweight]= Values[itemidx];
//				return Values[itemidx];
//			}else{
//				cache[itemidx][remainweight]= 0;
//				return 0;
//			}
//		}
//		
//		if(cache[itemidx][remainweight]!=-1){
//			return cache[itemidx][remainweight];
//		}
//		
//
//		
//		cache[itemidx][remainweight] = getMaxValues(itemidx-1, remainweight);
//		if(Weight[itemidx] < remainweight){
//			cache[itemidx][remainweight] =  Math.max(getMaxValues(itemidx-1, remainweight-Weight[itemidx])+Values[itemidx], cache[itemidx][remainweight]);
//		}
//		
//		System.out.println("itemidx:"+itemidx+"/remainweight="+remainweight);
//		System.out.println("getMaxValues()<<<<<<<<<<<<<<<<<<<<<<<<<<");
//		
//		return cache[itemidx][remainweight];
//	}
	
	public static void printCache(){
		for(int x=0;x<N;x++){
			System.out.print(x+ ":");
			for(int y=0;y<W+1;y++){
				System.out.print(cache[x][y]+" ");
			}
			System.out.println(" ");
		}
	}

}
