package Strategy_Pattern;

public class AObj {
	
	Ainterface ainterface;
	
	public AObj() {
		//�������̽� �Ҵ�
	ainterface = new Ainterface_impl();
	}
	
	public void funcAA() {
		
		// � ����� ������ ��, �� å���� �ٸ� ��ü�� ���ѱ� �ٸ� ��ü�� ����� �� ��������Ʈ �Ѵ� (�����Ѵ�)
		ainterface.funcA();
		ainterface.funcA();
//		System.out.println("funcAA()");
//		System.out.println("funcAA()");
		
		// ~ ����� �ʿ��մϴ�. �������ּ���
	}

}
