package sw.eng.test;

import java.util.Arrays;

public class NQueen {
	private  int cnt =0;
	public static void main(String[] args) {
		long start = System.nanoTime();
		NQueen nq = new NQueen();
		int size = 4+1;
		int[] n =new int[size];
		nq.setQueens(0, 0, n, size);
		long end = System.nanoTime();
		System.out.println(nq.cnt);
		System.out.println("Elapsed Time="+(end-start));
	}
	
	public void setQueens(int row,int col,int[] pos,int n){
		if(checkQueenPositon(row, pos)){
			if(row == n-1){
//				System.out.println(Arrays.toString(pos));
				cnt++;
			}else{
				for(int k=1;k<n;k++){
					int[] next = Arrays.copyOf(pos, n);
					next[row+1]=k;
					setQueens(row+1, k,next,n);
				}
			}
		}else{
//			System.out.println("Not Correct="+Arrays.toString(pos));
		}
 	}
	
	public boolean checkQueenPositon(int i,int[] pos){
		boolean rslt=true;
		
		for(int col=1;col<i;col++){
			if(pos[i]==pos[col]){
				rslt = false;
				break;
			}else if((i-col)==Math.abs(pos[i]-pos[col])){
				rslt=false;
				break;
			}
		}
		
		return rslt;
	}
	
}
