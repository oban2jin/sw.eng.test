package sw.eng.pro.y2017m02w4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class T{
	int id,depth,lcnt,rcnt;
	int lca,lca_lr;
	ArrayList<Integer> list;
	T p,lc,rc;
	public T(int id){
		this.id=id; this.p=this;
		list = new ArrayList<Integer>();
	}
	public void addL(T lc){
		this.lc=lc;
		this.lc.p=this;
		this.lc.depth=this.depth+1;
	}
	public void addR(T rc){
		this.rc=rc;
		this.rc.p=this;
		this.rc.depth=this.depth+1;
	}
	public void addlist(int n){
		this.list.add(n);
	}
	public String toString(){
		return id+"/"+list+":"+p.id+"|"+depth+"/lca="+lca+"/lca_lc="+lca_lr+" ("+lcnt+","+rcnt+")";
	}
}

public class Solution {
	public static int MAXN=16;
	public static int T,N;
	public static int[][] sp;
	public static T[] Tlist;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("/Users/oban2jin/Documents/workspace/sw.eng.test/src/sw/eng/pro/y2017m02w4/sample.txt"));
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++){
			N=Integer.parseInt(br.readLine()); Tlist = new T[N+1]; sp = new int[N+1][MAXN];
			for(int n=1;n<N+1;n++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken()); int lr=Integer.parseInt(st.nextToken()); int a=Integer.parseInt(st.nextToken());
				sp[n][0]=p;
				T pt=null; T ct=null;
				if(p!=0){
					if(Tlist[p]==null){
						Tlist[p]=new T(p);
					}else{
						pt=Tlist[p];
					}
					if(Tlist[n]==null){
						Tlist[n]=new T(n);
						ct=Tlist[n];
					}else{
						ct=Tlist[n];
					}
					if(lr==0){pt.addL(ct);}else{pt.addR(ct);}
				}else{
					if(Tlist[n]==null){
						Tlist[n]=new T(n);
						ct=Tlist[n];
					}else{
						ct=Tlist[n];
					}
					pt=Tlist[n];
				}
				for(int i=1;i<a+1;i++){
					ct.addlist(Integer.parseInt(st.nextToken()));
				}
				
			}
			
			sparsetable();
			System.out.println(nodecnt(Tlist[1]));
			
			for(int n=1;n<N+1;n++){
				T a = Tlist[n];
				for(int i=0;i<Tlist[n].list.size();i++){
					T b =  Tlist[Tlist[n].list.get(i)];
					if(a.depth==b.depth){
						if(a==b){
							a.lca=b.id; a.lca_lr=-1; break;
						}
					}else if(a.depth>b.depth){
						T lca=a;
						for(int idx=0;idx<MAXN;idx++){
							if(((1<<idx)&(lca.depth-b.depth-1))!=0){
								lca = Tlist[sp[lca.id][idx]];
							}
						}
						if(a.lca==0){
							if(b.lc.id==lca.id){
								a.lca=b.id; a.lca_lr=0;
							}else{
								a.lca=b.id; a.lca_lr=1;
							}
						}else{
							if(Tlist[a.lca].depth>lca.depth){
								if(b.lc.id==lca.id){
									a.lca=b.id; a.lca_lr=0;
								}else{
									a.lca=b.id; a.lca_lr=1;
								}
							}
						}
					}
				}
			}
			for(T x : Tlist){
				System.out.println(x);
			}
		}
		
	}
	
	public static int nodecnt(T root){
		int rslt=0;
		if(root.lc!=null){
			root.lcnt=nodecnt(root.lc);
			rslt=rslt+root.lcnt;
		}
		if(root.rc!=null){
			root.rcnt=nodecnt(root.rc);
			rslt=rslt+root.rcnt;
		}
		if(root.lc==null&&root.rc==null){
			root.rcnt=0; root.lcnt=0;
			return 1;
		}
		return rslt+1;
	}
	
	public static void sparsetable(){
		for(int n=1;n<N+1;n++){
			for(int i=1;i<MAXN;i++){
				sp[n][i]=sp[sp[n][i-1]][i-1];
			}
		}
	}

}
