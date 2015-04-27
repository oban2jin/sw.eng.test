package sw.eng.test;

import java.util.Stack;
import java.util.Vector;

public class GraphSearch {
	private Stack<String> answers = new Stack<String>();
	private Vector<String> findPath = new Vector<String>();
	private Stack<String> brach  = new Stack<String>();
	private int startNode;
	
	private int[][] path ={{1,1,0,1,1,0},
			               {1,1,1,1,1,0},
			               {0,1,1,0,0,1},
			               {1,1,0,1,0,0},
			               {1,1,0,0,1,1},
			               {0,0,1,0,1,1}};
	
	public static void main(String[] args) {
		GraphSearch gs = new GraphSearch();
		gs.doSomething();
		gs.showResults();
	}
	
	public void showResults(){
		while(!answers.isEmpty()){
			System.out.println(answers.pop());
		}
	}
	
	public void doSomething(){
		
		this.startNode = 3;
		findPath.add(Integer.toString(startNode));
		this.findNextNode(startNode, 2);
		
		while(!brach.isEmpty()){
			String Node = brach.pop();
			
			int curNode = Integer.parseInt(Node.substring(0,1));
			int curNodeDepth = Integer.parseInt(Node.substring(2));
				
			this.findPath = this.clearPath(curNodeDepth-1);
			findPath.add(Integer.toString(curNode));

			//다음 노드 길 찾기 			
			this.findNextNode(curNode,curNodeDepth+1);
			
			//젇압 체크 
			if(findPath.size()==6){
				this.testCorrect();
			}

		}
	}
	
	private void testCorrect(){
		int lastNode = Integer.parseInt(findPath.lastElement());
		if(path[lastNode-1][this.startNode-1]==1){
			findPath.add(Integer.toString(this.startNode));
			answers.push(findPath.toString());
		}
	}
	
	private void findNextNode(int curNode, int curNodeDepth){
		for(int i=0;i<6;i++){
			if(i!=curNode-1){
				if(this.path[curNode-1][i] == 1)
					if(!findPath.contains(Integer.toString(i+1))){
						brach.push(Integer.toString(i+1)+"/"+Integer.toString(curNodeDepth));
					}
			}
		}
	}
	
	private Vector<String> clearPath(int pos){
		Vector<String> rslt = new Vector<String>(findPath.subList(0, pos));
		return rslt;
	}

}
