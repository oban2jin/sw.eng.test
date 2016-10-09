package sw.eng.bank;

import java.util.Vector;

public class Sudoku {
   private int[][] given = 
	{{0,0,0,0,0,0,0,0,0,0},
     {0,1,4,5,2,3,6,7,8,9},
	 {0,6,2,4,1,8,9,3,4,5},
	 {0,8,9,3,4,5,7,1,2,6},
	 {0,2,1,4,3,6,8,5,9,7},
	 {0,3,5,6,7,9,3,4,1,8},
	 {0,7,8,9,5,1,4,2,6,3},
	 {0,4,3,8,6,7,1,9,5,2},
	 {0,5,6,1,9,2,3,8,1,4},
	 {0,9,7,2,8,4,5,6,3,1}};
	
   private String[][] rslt =
	       {{"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""},
		    {"","","","","","","","","",""}};
   
   public static void main(String[] args) {
	   long start = System.nanoTime();
		Sudoku sk = new Sudoku();
		sk.doSomething();
		long end = System.nanoTime();
		
		System.out.println("Elapsed Time="+(end-start));
		
		for(String[] x : sk.rslt){
			for(String xx : x ){
				System.out.print(xx+",");
			}
			System.out.println("");
		}
	}
   
   public void doSomething(){
	   Vector<Integer> temp = new Vector<Integer>();
	   
	   //��������� ���� �˻�
	   for(int row=1 ; row<10;row++){
		   temp.clear();
		   for(int col=1;col<10;col++){
			   if(temp.contains(given[row][col])){
				   int dupcol = temp.indexOf(given[row][col]);
				   rslt[row][dupcol+1]="x";
				   rslt[row][col]="x";
			   }else{
				   rslt[row][col]=Integer.toString(given[row][col]);
			   }
			   temp.add(given[row][col]);
		   }
//		   System.out.println(temp);
	   }
	   
	   //���������� ���� �˻�
	   for(int col=1 ; col<10;col++){
		   temp.clear();
		   for(int row=1;row<10;row++){
			   if(temp.contains(given[row][col])){
				   int duprow = temp.indexOf(given[row][col]);
				   rslt[duprow+1][col]=rslt[duprow+1][col]+"/"+"y";
				   rslt[row][col]=rslt[row][col]+"/"+"y";
			   }else if("x"!=(rslt[row][col])){
				   rslt[row][col]=Integer.toString(given[row][col]);
			   }
			   temp.add(given[row][col]);
		   }
	   }
   }

}
