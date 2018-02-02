package rough;

public class Test2 {
	
	public String getClassName1(){
		return this.toString();
	}

	public String getClassName2(String sTestCaseName){
		int start_posi = sTestCaseName.indexOf(".")+1;
		int end_posi = sTestCaseName.indexOf("@");
		return sTestCaseName.substring(start_posi, end_posi); 
		
	}
	
	public static void main(String[] args) {
		
		Test2 obj1 = new Test2();
		System.out.println(obj1.getClassName2(obj1.getClassName1()));

	}

}
