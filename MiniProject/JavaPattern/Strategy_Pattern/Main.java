package Strategy_Pattern;

public class Main {

	// ����� ��� 
	public static void main(String[] args) {

		Ainterface ainterface = new Ainterface_impl();
		
		//���
		ainterface.funcA();
		
		System.out.println("---");
		//��������Ʈ 
		AObj aObj = new AObj();  
		aObj.funcAA();
	}

}
