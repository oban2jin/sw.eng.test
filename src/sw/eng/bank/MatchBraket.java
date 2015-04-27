package sw.eng.bank;

import java.util.Stack;

public class MatchBraket {
	private Stack<String> st = new Stack<String>();
	private String strInput = ")(((()))()())";
	int pos = 8;
	
	public static void main(String[] args) {

		MatchBraket mb = new MatchBraket();
		System.out.println("rslt="+mb.doSomething(mb.strInput));
	}
	
	public int doSomething(String strInput){
		int rslt = 0;
		
		String xx = strInput;
		
		for(int i=0;i<strInput.length();i++){
			String y = xx.substring(0,1);
			xx = xx.substring(1);
			
			System.out.println("i=["+i+"]="+y);
			System.out.println("i=["+i+"]="+xx);
			
			if("(".equals(y)){
				if(i+1 == pos)
					y="*";
				st.push(y);
			}else{
				if(!st.isEmpty()){
					String z = st.pop();
					if("*".equals(z))
						rslt = i+1;
				}else{
					break;
				}
			}
		}
		
		if (!st.isEmpty())
			rslt = 0;
		
		return rslt;
	}

}
