package pretest.tour;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReMeet {
	public static int T,N,M,K;
	public static float[][] Graph;
	public static float[][] Probability,Probability2;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/pretest/tour/ReMeet.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Graph = new float[N+1][N+1];
			Probability  = new float[M+1][N+1];
			Probability2 = new float[M+1][N+1];
			
			for(int k=0;k<K;k++){
				st = new StringTokenizer(br.readLine());
				int startnode = Integer.parseInt(st.nextToken());
				int endnode = Integer.parseInt(st.nextToken());
				Float probability = Float.parseFloat(st.nextToken());
				Graph[startnode][endnode] = probability;
			}
			System.out.println("Graph=> \n"+printArray2(Graph));
			
			Probability[1][1] = 1;
			Probability2[1][N] = 1;
//			System.out.println("Probability=> \n"+printArray2(Probability));
//			System.out.println("Probability2=> \n"+printArray2(Probability2));
			
			for(int m=2;m<M+1;m++){
				for(int start=1;start<Probability[m-1].length;start++){
					if(Probability[m-1][start]!=0){
//						System.out.println((m-1)+"day=/start="+start+"/"+Probability[m-1][start]);
						for(int next=1;next<N+1;next++){
							if(Graph[start][next]!=0){
//								System.out.println("next="+next+"/"+Graph[start][next]);
								Probability[m][next] = Probability[m][next] + Probability[m-1][start]*Graph[start][next]; 
							}
						}
					}
				}
//				System.out.println("Probability=> \n"+printArray2(Probability));
//				System.out.println("***********************");
				for(int start=1;start<Probability2[m-1].length;start++){
					if(Probability2[m-1][start]!=0){
//						System.out.println((m-1)+"day=/start="+start+"/"+Probability2[m-1][start]);
						for(int next=1;next<N+1;next++){
							if(Graph[start][next]!=0){
//								System.out.println("next="+next+"/"+Graph[start][next]);
								Probability2[m][next] = Probability2[m][next] + Probability2[m-1][start]*Graph[start][next]; 
							}
						}
					}
				}
//				System.out.println("***********************");
			}
			System.out.println("Probability=> \n"+printArray2(Probability));
			System.out.println("Probability2=> \n"+printArray2(Probability2));
			
			System.out.println("#"+(t+1)+" "+getProbality(1,1));
		}
		
	}//End-of-Main
	
	/*
	 * 
	 */
	public static float getProbality(int node,int day){
		float rslt = 0;
		
		//해당일에 만났는지 확인 
		if((Probability[day][node]!=0)&&(Probability2[day][node]!=0)){
			rslt = Probability[day][node]*Probability2[day][node];
			System.out.println("Meet!!!"+day+"/"+node+"/"+rslt);
		}else{
			if(day<M){
				for(int next=1;next<N+1;next++){
					if(Graph[node][next]!=0){
						rslt = rslt + getProbality(next, day+1);
					}
				}
			}
		}
		return rslt;
	}
	
	public static String printArray(int[] A){
		StringBuffer sb = new StringBuffer();
		for(int n=0;n<A.length;n++){
			sb.append(A[n]+" ");
		}
		return sb.toString();
	}
	public static String printArray2(float[][] A){
		StringBuffer sb = new StringBuffer();
		for(int n=1;n<A.length;n++){
			for(int i=1;i<A[n].length;i++){
				sb.append(A[n][i]+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
