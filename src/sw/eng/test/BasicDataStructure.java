package sw.eng.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BasicDataStructure {
	
	private Queue<String> queue = new LinkedList<String>();
	private Stack<String> stack = new Stack<String>();;

	public static void main(String[] argv){
		BasicDataStructure bd = new BasicDataStructure(argv);
		bd.doSomething();
	}
	
	public BasicDataStructure (String[] arr){
		 for(int i=0; i<arr.length; i++){
			 stack.push(arr[i].toString());
		 }
		 
		 for(int i=0; i<arr.length; i++){
			 queue.add(arr[i].toString());
		 }		 
		 
	}

	public void doSomething(){
		while(!queue.isEmpty()){
			System.out.println("queue=["+queue.poll()+"]");
		}
		
		while(!stack.empty()){
			System.out.println("stack=["+stack.pop()+"]");			
		}
	}
}
