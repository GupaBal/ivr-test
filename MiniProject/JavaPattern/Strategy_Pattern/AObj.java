package Strategy_Pattern;

public class AObj {
	
	Ainterface ainterface;
	
	public AObj() {
		//인터페이스 할당
	ainterface = new Ainterface_impl();
	}
	
	public void funcAA() {
		
		// 어떤 기능을 구현할 때, 그 책임을 다른 객체로 떠넘김 다른 객체를 사용할 때 델리게이트 한다 (위임한다)
		ainterface.funcA();
		ainterface.funcA();
//		System.out.println("funcAA()");
//		System.out.println("funcAA()");
		
		// ~ 기능이 필요합니다. 개발해주세요
	}

}
