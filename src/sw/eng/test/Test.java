package sw.eng.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		Test tst = new Test();
		
		long start = System.nanoTime();		
		tst.doSomething();
		tst.doSomething2();
		long end = System.nanoTime();
		
		System.out.println("걸린시간:"+(end-start)+" ns");
	}
	
	public void doSomething(){
		ArrayList<Integer> xx = new ArrayList<Integer>();
		
		xx.add(8);
		xx.add(10);
		xx.add(1);
		xx.add(0);
		
		Collections.sort(xx);
		
		ArrayList<Integer> yy = new ArrayList<Integer>();
		
		yy.add(8);
		yy.add(10);
		yy.add(1);
		yy.add(5);

		Collections.sort(yy);
		
		System.out.println(xx);
		System.out.println(yy);
		
		System.out.println(xx.containsAll(yy));
	}
	
	public void doSomething2(){
		HashMap<String,Integer> xx = new HashMap<String, Integer>();
		
		xx.put("obanjin",20222);
		
		System.out.println(xx.get("obanjin"));
		
		HashMap<Integer,HashMap<String,Integer>> yy = new HashMap<Integer, HashMap<String,Integer>>();
	}

}
