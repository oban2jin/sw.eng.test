package sw.eng.test;

public class JavaAPI {
	final static String AA = "B";
	final static String AB = "A";	
	final static String BA = "B";
	final static String BB = "A";	
	
	public static void main(String[] args) {
		JavaAPI ja = new JavaAPI();
		String xx = "BAAAAB";
		System.out.println("result="+ja.doSomethig(xx));

	}
	
	public String doSomethig(String strInput){
		String xx ="";
				
		if(strInput.length()>1){
//			System.out.println(strInput.substring(0, strInput.length()-2) );
//			System.out.println(strInput.substring(strInput.length()-2, strInput.length()));
//			System.out.println(this.parseToken((strInput.substring(strInput.length()-2, strInput.length()))));
			xx = this.doSomethig(strInput.substring(0, strInput.length()-2) + this.parseToken((strInput.substring(strInput.length()-2, strInput.length()))));			
		}else{
			xx=strInput;
		}
		
		return xx;
	}
	
	private String parseToken(String strInput){
		String xx="";
		switch (strInput) {
			case "AA":
				xx = AA;
				break;
			case "AB":
				xx = AB;
				break;
			case "BA":
				xx = BA;
				break;
			case "BB":
				xx = BB;
				break;				
			default:
				//파잇 오류 처리를 여기서 해야 함
				break;
		}
		
		return xx;
	}
	
	
}
