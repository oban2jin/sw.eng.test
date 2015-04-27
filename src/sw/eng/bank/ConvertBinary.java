package sw.eng.bank;

public class ConvertBinary {
	
	public static void main(String args[]){
		ConvertBinary cb = new ConvertBinary();
		cb.doSomething();
	}
	
	public void doSomething(){
		int hexNo = 1024;
		String binNo = Integer.toBinaryString(hexNo);
		String rbinNo = new StringBuffer(binNo).reverse().toString();
		int rhexNo = Integer.parseInt(rbinNo, 2);
		System.out.println("Reverse="+rhexNo);
	}
}
