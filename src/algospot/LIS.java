package algospot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class LIS {
	
	public static int C;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/LIS.txt"));
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine());
		
		for(int c=0;c<C;c++){
			String[] N = br.readLine().split(" ");
			
			Vector<Integer> A = new Vector<Integer>();
			for(int n=0;n<N.length;n++){
				A.add(Integer.parseInt(N[n]));
			}
			
			System.out.println(lis(A));
		}
	}
	
	public static int lis(Vector<Integer> A){
		System.out.println("A="+A);
		
		int rslt = 0;
		
		if(A.size()==0)
			return 0;
		
		for(int i=0;i<A.size();i++){
			Vector<Integer> B = new Vector<Integer>();
			for(int j=i+1;j<A.size();j++){
				if(A.get(i)<A.get(j)){
					B.add(A.get(j));
				}
			}
			rslt = Math.max(rslt,lis(B)+1);
		}
		
		return rslt;
	}
}
