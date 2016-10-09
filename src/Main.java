
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	static int C;
	static int N;
	static int[][] triangle;
	static int[][] cache;
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/jungol/TrianglePath.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		C = Integer.parseInt(br.readLine());
		
		for(int c=0;c<C;c++){
			N = Integer.parseInt(br.readLine());
			triangle = new int[N][N];
			cache = new int[N][N];
			
			for(int x=0;x<N;x++){
				String[] values = br.readLine().split(" ");
				for(int y=0;y<values.length;y++){
					triangle[x][y] = Integer.parseInt(values[y]);
				}
			}
			
			for(int x=0;x<N;x++){
				for(int y=0;y<N;y++){
					cache[x][y] = -1;
				}
			}
			
			System.out.println(findMaxValuePath(0, 0));
		}
		
		
	}
	
	public static int findMaxValuePath(int x , int y){
//		System.out.println("x/y="+x+"/"+y);
		
		int rslt = cache[x][y];
		if(rslt != -1){
//			System.out.println("Hit Memozation!!");
			return rslt;
		}
		
		if(x==N-1){
			cache[x][y]  = triangle[x][y];
			return triangle[x][y];
		}
		
		rslt = Math.max(findMaxValuePath(x+1, y),findMaxValuePath(x+1, y+1)) + triangle[x][y];
		cache[x][y]  = rslt;
		
		return rslt;
	}
	

}
