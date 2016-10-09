package sw.eng.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MapSorting {
	
	public static void main(String[] args) {
		
//		HashMap<String, Integer> X = new HashMap<String, Integer>();
//		X.put("B", 1);
//		X.put("A",2);
//		
//		
//		int x = X.get("A");
//		
//		System.out.println(X);
//		
//		System.out.println(x);
		
		
		ArrayList<Integer> A = new ArrayList<Integer>();
		
		A.add(2); A.add(5); A.add(3); A.add(6); A.add(1);
		System.out.println(A);
		
		Collections.sort(A);
		
		System.out.println(A);
		
	}
}
