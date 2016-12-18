package algospot.gallery;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Gallery {

	public static int C,G,H;
	public static int[][] graph;
	public static boolean[] visited;
	public static final int NOWATCHED = 0;
	public static final int WATCHED = 1;
	public static final int INSTALLED = 2;

	public static int installed_cammera;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/gallery/Gallery.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		C = Integer.parseInt(br.readLine());
		for(int c=0;c<C;c++){
			String[] inP = br.readLine().split(" ");
			G = Integer.parseInt(inP[0]); 
			H = Integer.parseInt(inP[1]); 

			graph = new int[G][G];
			visited = new boolean[G];
			installed_cammera = 0;

			for(int h=0;h<H;h++){
				String[] inG = br.readLine().split(" ");
				int here  = Integer.parseInt(inG[0]);
				int there = Integer.parseInt(inG[1]);
				graph[here][there] = 1;
			}

		}

		printInput();
		printGraph();

		visitAll();
	}

	public static void visitAll(){
		for(int g=0;g<G;g++){
			if((!visited[g])&&(dfs(g,1)==NOWATCHED)){
				installed_cammera++;
			}
		}
		System.out.println(installed_cammera);
	}

	public static int dfs(int here,int depth){
		System.out.println("*************dfs*************");
		for(int i=0;i<depth;i++){
			System.out.print("->");
		}
		System.out.println("visited="+here);

		int[] children = {0,0,0};
		visited[here]=true;

		for(int there=0;there<G;there++){
			if(graph[here][there]==1){
				if(!visited[there]){
					visited[there] = true;
					children[dfs(there,depth+1)]++;
				}
			}
		}

		if(children[NOWATCHED]>0){
			installed_cammera++;
			return INSTALLED;
		}

		if(children[INSTALLED]>0){
			return WATCHED;
		}

		return NOWATCHED;
	}

	public static void printInput(){
		System.out.println("C="+C+"/G="+G+"/H="+H);
	}

	public static void printGraph(){
		for(int i=0;i<G;i++){
			for(int j=0;j<G;j++){
				System.out.print(graph[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
}
