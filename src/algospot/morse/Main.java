package algospot.morse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int T,N,M,K;
	public static int[][] bino;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/algospot/morse/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			bino = new int[2*N+1][2*N+1];
			preCal();
//			genSignal(N, M, "");
			bw.write(KTH(N,M,K));
		}
		bw.flush();
	}
	
	public static void preCal(){
		bino[0][0]=1;
		for(int i=0;i<2*N+1;i++) for(int j=0;j<i+1;j++){
			if(j==0||i==j){
				bino[i][j]=1;
			}else{
				bino[i][j] = bino[i-1][j-1] + bino[i-1][j];
			}
		}
	}
	
	public static void genSignal(int n,int m,String signal){
//		System.out.println(n+"/"+m);
		if(m==0&&n==0){
			System.out.println(signal);
		}
		
		if(n>0) genSignal(n-1, m, signal+"-");
		if(m>0) genSignal(n, m-1, signal+"o");
	}
	
	public static String KTH(int n,int m, int k){
		System.out.println(n+"/"+m);
		if(n==0){
			String rslt="";
			for(int i=1;i<m+1;i++) rslt=rslt+"o";
			return rslt;
		}
		if(bino[m+n-1][n-1]<k){
			return "o"+KTH(n,m-1,k-bino[m+n-1][n-1]);
		}else{
			return "-"+KTH(n-1,m,k);
		}
	}

}
