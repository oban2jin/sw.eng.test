package sw.eng.test;

public class BackTraking {
	private int[][] maze;
	private int row;
	private int col;
	
	public static void main(String[] args) {
		BackTraking bk = new BackTraking();
		
		bk.doSomething();
	}
	
	public BackTraking(){
		this.maze = new int[][]{{1,0,1,1,1,1},
								{1,0,1,0,1,0},
								{1,0,1,0,1,1},
								{1,1,1,0,1,1}};
		
		this.row = this.maze.length;
		this.col = this.maze[0].length;
//		System.out.println(row+"/"+col);
	}
	
	public void doSomething(){
		searchPath(0, 0);
		for(int[] xx : this.maze){
			for(int i=0;i<xx.length;i++){
				System.out.print(xx[i]+" ");
			}
			System.out.println("");
		}
	}
	
	public void searchPath(int x,int y){
		System.out.println("maze["+x+"]["+y+"]="+maze[x][y]);
		if((x==row-1) && (y==col-1))
			return;

		if(((y+1)<col)&&(maze[x][y+1]==1)){
		//오른쪽
			maze[x][y+1] = maze[x][y]+1;
			searchPath(x,y+1);
		}
		if(((y-1)>=0)&&(maze[x][y-1]==1)){
		//왼쪽
			maze[x][y-1] = maze[x][y]+1;
			searchPath(x,y-1);			
		}
		if(((x-1)>=0)&&!((x-1)==0 && y==0)&&(maze[x-1][y]==1)){
		//위쪽
			maze[x-1][y] = maze[x][y]+1;
			searchPath(x-1,y);			
		}
		if(((x+1)<row)&&(maze[x+1][y]==1)){
		//아래쪽 	
			maze[x+1][y] = maze[x][y]+1;
			searchPath(x+1,y);			
		}
	}
}
