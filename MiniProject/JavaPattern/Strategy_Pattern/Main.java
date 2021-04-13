package Strategy_Pattern;

public class Main {

	// 기능의 통로 
	public static void main(String[] args) {

		Ainterface ainterface = new Ainterface_impl();
		
		//통로
		ainterface.funcA();
		
		System.out.println("---");
		//델리게이트 
		AObj aObj = new AObj();  
		aObj.funcAA();
	}

}
