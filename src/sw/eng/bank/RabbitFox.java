package sw.eng.bank;

class RabbitFox {
	private int [][] fields = new int[20+1][20+1];
	private int rabitCnt;
	
	public static void main(String[] args) {
		RabbitFox rf = new RabbitFox();
		
		//¿©¿ì
		rf.fields[6][10]=9;

		//Åä³¢µé
		rf.fields[5][6]=1;
		rf.fields[2][3]=1;
		rf.fields[15][16]=1;
		rf.fields[9][2]=1;		
		rf.fields[16][6]=1;
		
		rf.doSomething();
	}
	
	public void doSomething(){
		
	}
}
