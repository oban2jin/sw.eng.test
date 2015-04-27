package sw.eng.bank;

import java.util.Stack;
import java.util.Vector;

public class CriticalCity {
	
	private int[][] path;
	private Stack<Vector> routes = new Stack<Vector>();	
	
	public CriticalCity() {
		this.path = new int[][]{{0,0,0,0,0,0,0,0,0},
								{0,0,0,1,0,1,0,0,0},
								{0,0,0,1,0,0,1,1,0},
								{0,1,1,0,0,0,1,0,0},
								{0,0,0,0,0,1,0,0,0},
								{0,1,0,0,1,0,0,0,0},
								{0,0,1,1,0,0,0,0,0},
								{0,0,1,0,0,0,0,0,1},
								{0,0,0,0,0,0,1,0,0}};		
	}

	public static void main(String[] args) {
		CriticalCity cc = new CriticalCity();
		cc.doSomething();
	}
	
	public void doSomething(){
		boolean flag = true;
		
		for(int city=1;city<9;city++){
			Vector<Integer> nodes = findNextNodeNo(city);
			flag=true;
//			System.out.println("city=["+city+"]");			
			if(nodes.size()>=2){
				for(int x=0;x<nodes.size();x++){
					for(int j=x+1;j<nodes.size();j++){
						if(!findPath(nodes.get(x), nodes.get(j),city)){
							System.out.println("critical city=["+city+"]");
							flag = false;
							break;
						}	
					}
					if(!flag)
						break;
				}
			}
		}
	}
	
	private boolean findPath(int startNode, int endNode, int removecity){
		boolean rslt = false;
		
		routes.clear();
		
		Vector<Integer> curPath = new Vector<Integer>();
		curPath.add(startNode);
		routes.add(curPath);

		while(!routes.isEmpty()){
		    Vector<Integer> curroute = (Vector<Integer>)routes.pop();
			int lastNodeNo = (Integer)curroute.lastElement();
			
			Vector<Integer> nextNodes = findNextNodeNo(lastNodeNo);
			
			for(int i=0;i<nextNodes.size();i++){
				Vector<Integer> newroute = (Vector<Integer>)curroute.clone();
				
				if(newroute.contains(endNode)){
					//Start Node에서 End Node로 Path가 존재
//					System.out.println("startNode=["+startNode+"]/endNode=["+endNode+"]");					
//					System.out.println("Find the Cycle Path="+newroute);
					rslt = true;
					return rslt;
				}else if(!newroute.contains(nextNodes.get(i)) && (removecity!=nextNodes.get(i))){
					newroute.add(nextNodes.get(i));
					routes.push(newroute);
				}
			}
		}
		
		return rslt;
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
					//사이클 여부 검사
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
//				System.out.println("next=["+i+"]");
			}
		}
		
		return nextNodes;
		
	}
}
