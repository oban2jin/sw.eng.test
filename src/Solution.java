import java.util.Scanner;

public class Solution {
	
    static int wlen;
    static int start = 0;
    static int end = 0;
    static char[] ws;
    static int matchCnt = 0;
    static int unmatchCnt = 0;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        String W = sc.next();
        ws = W.toCharArray();
        
        wlen = ws.length;
        
        boolean match = true;
        boolean loopchk = true;
        
    	do{
        	String prefix="";
        	String suffix="";
        	String rsuffix="";
        	
        	if((end+1)==wlen){
    			System.out.print(end+1);
    			System.out.print(" ");     		
        		break;
        	}
        	
    		for(int x = start ; x < end+1 ; x++){
  System.out.println("x/end:"+x+"/"+end);
  System.out.println("ws[x]:"+x+"/"+ws[x]);
  System.out.println("ws[wlen-end+x-1]:"+(wlen-end+x-1)+"/"+ws[wlen-end+x-1]);   			
    			match = true;
			   if( (ws[x] != ws[wlen-end+x-1]) || (ws[wlen-start-end+x-1] != ws[end-start+x-1]) )
			   {
                   match = false;
                   break;
               }
    		}
    		
    		if(match){
    			
    			unmatchCnt = end +1;
    			
    			if(matchCnt==0)
    				matchCnt = end+1;
    			
    			start = end+1;
    			
    			System.out.print(end+1);
    			System.out.print(" ");
    			
        		end = Math.min(end + matchCnt,wlen-1);
        		
    		}else{
    			
    			if(unmatchCnt !=0){
//System.out.println("unmatchCnt/start/end="+unmatchCnt+"/"+start+":"+end);
    				end = Math.min(start + unmatchCnt,wlen-1);
    			}else if( (matchCnt ==0) && (end >0) ){
    				end = Math.min((end+1)*2-1, wlen-1);
    			}else {
    				end++;
    			}
    			start = unmatchCnt;
    			
    			matchCnt = 0;
    		}
    		
    	}while(loopchk);

    }
    
    public static boolean reverseString(int end){
    	boolean rslt = true;
    	System.out.println("end="+end);
    	System.out.println("wlen="+wlen);
    	
    	for(int x = 0; x < end+1; x++){
    		System.out.println("ws[x]="+x+"/"+ws[x]);
    		System.out.println("ws[wlen-x-1]="+(wlen-x-1)+"/"+ws[wlen-x-1]);
 
    		if(ws[x] != ws[wlen-x-1]){
    			rslt = false;
    			break;
    		};
    	}
    	return rslt;
    }
    
}
