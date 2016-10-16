package sw.eng.test;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		
		ArrayList<Integer> C;
		
		A.add(1); A.add(2); A.add(4);
		System.out.println(A);
		B.add(5);
		
		System.out.println(A);
	}
}
