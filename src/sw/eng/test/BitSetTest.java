package sw.eng.test;

import java.util.BitSet;

public class BitSetTest {

	public static void main(String[] args) {
		
		BitSet A = new BitSet();
		
		 A.set(4); A.set(0);
		
		System.out.println(getIntNumber(A));
		
		int xx = 10;
		String yy =  Integer.toBinaryString(xx);
		String[] zz = yy.split("");
	}
	
	public static int getIntNumber(BitSet binaryNumber){
		int rslt=0;
		
		for(int i=0;i<binaryNumber.length();i++){
			if(binaryNumber.get(i)){
				rslt = rslt + (1<<i);
			}
		}
		
		return rslt;
	}
}
