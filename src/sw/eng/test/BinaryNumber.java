package sw.eng.test;

import java.util.Queue;
import java.util.Stack;

public class BinaryNumber {
	private String strInput;
	private int intHexOutput;
	
	private String rsvBinaryNumber[];	
	private Stack<String> stack = new Stack<String>();;

	
	public static void main(String[] args) {
		BinaryNumber bn = new BinaryNumber(args[0]);
		bn.doSomeThing();
	}
	
	public BinaryNumber (String strInput){
		this.strInput = strInput;
	}
	
	public void doSomeThing(){
		//이진수 변환 
		this.makeBinaryNumber();
		//치환 
		this.reverseBinaryNumber();
		//10진수로 변환
		this.makeHexNumber();
		
	}
	
	private void makeBinaryNumber(){
		int x = Integer.parseInt(strInput);
		int y,z;
		
		System.out.println("x=["+x+"]");
		
		for(;;){
			y = x/2;
			z = x%2;
			stack.push(Integer.toString(z));
			
			if(y<2){
				stack.push(Integer.toString(y));
				break;
			}else{
				x = y;
			}
		}
		
	}
	
	private void reverseBinaryNumber(){
		rsvBinaryNumber = new String[stack.size()];
		int i = rsvBinaryNumber.length-1;
		while(!stack.isEmpty()){
			rsvBinaryNumber[i] = stack.pop();
			i--;
		}
	}
	
	private void makeHexNumber(){
		for(int i=0;i<rsvBinaryNumber.length;i++){
			System.out.print(rsvBinaryNumber[i]);
			intHexOutput = (int) (intHexOutput + Integer.parseInt(rsvBinaryNumber[i])*Math.pow(2,(rsvBinaryNumber.length-1-i)));
		}
		System.out.println("\nintHexOutput=["+intHexOutput+"]");
	}
	
	public String toString(){
		System.out.println("stack.capa="+stack.size());
		String xx ="";
		while(!stack.empty()){
			xx = xx+stack.pop();
		}
		return xx; 
	}
	
	
	

}
