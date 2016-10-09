package sw.eng.bank;

import java.util.Stack;
import java.util.Vector;

public class FindCycleV2 {
	private int[][] path;
	private Stack<Vector> routes = new Stack<Vector>();
	
	public static void main(String args[]){
		long start = System.nanoTime();
		FindCycle fc = new FindCycle();
		fc.doSomething();
		long end = System.nanoTime();
		
		System.out.println("Elapsed Time(ns) = "+(end-start));
	}

	public FindCycleV2(){
		this.path = new int[][]{{0,0,0,0,0,0,0,0,0},
			                    {0,0,0,1,0,0,0,0,0},
			                    {0,0,0,0,0,0,0,0,0},
			                    {0,0,1,0,1,1,0,0,0},
			                    {0,0,0,0,0,0,1,0,0},
			                    {0,1,0,0,0,0,0,0,0},
			                    {0,0,0,0,0,0,0,0,1},
			                    {0,0,0,0,0,0,0,0,1},
			                    {0,0,0,0,0,0,0,1,0}};
	}
	
	public void doSomething(){
		for(int i=1;i<9;i++){
			findCycleFromNode(i);
		}
	}
	
	private void findCycleFromNode(int startNodeNo){
		routes.clear();
		
		Vector<Integer> curPath = new Vector<Integer>();
		curPath.add(startNodeNo);
		routes.add(curPath);
		
		while(!routes.isEmpty()){
		    Vector<Integer> curroute = (Vector<Integer>)routes.pop();
			int lastNodeNo = (Integer)curroute.lastElement();
			
			Vector<Integer> nextNodes = findNextNodeNo(lastNodeNo);
			
			for(int i=0;i<nextNodes.size();i++){
				Vector<Integer> newroute = (Vector<Integer>)curroute.clone();
				
				if(newroute.firstElement() == nextNodes.get(i)){
					//사이클 패스 발견 
					System.out.println("Find the Cycle Path="+newroute);
				}else if(!newroute.contains(nextNodes.get(i))){
					newroute.add(nextNodes.get(i));
//					System.out.println("push="+newroute);
					routes.push(newroute);
				}
			}
		}
	}
	
	private Vector<Integer> findNextNodeNo(int nodeNo){
		Vector<Integer> nextNodes = new Vector<Integer>();
		int[] nodePath = path[nodeNo];
		
		for(int i=1;i<nodePath.length;i++){
			if(nodePath[i]==1){
				nextNodes.add(i);
			}
		}
		return nextNodes;
	}

}
