package basic.unionfind;

class UnionFind{
	int[] p;
	
	public UnionFind(int size){
		p=new int[size];
		for(int n=0;n<p.length;n++) p[n]=n;
	}
	public int find(int a){
		if(a==p[a]) return a;
		return p[a]=find(p[a]);
	}
	public void union(int a,int b){
		a=find(a); b=find(b);
		if(a==b) return;
		if(a<b){
			a=a^b; b=b^a; a=a^b;
		}
		p[b]=a;
		return;
	}
}

public class Solution {

	public static void main(String[] args) {
		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(1, 9);
		for(int n=0;n<10;n++) System.out.println("uf.find()=>"+uf.find(n));
	}

}
