package TemplateMethod_Pattern;

public class DefaultGameConnectHelper extends AbstGameConnectHelper {

	@Override
	protected String doSecurity(String stirng) {
		
		System.out.println("�����ð��� ����� ��ȭ�� ���ڵ� �۾�");
//		System.out.println("���ڵ� �۾�");
		return stirng;
	}

	@Override
	protected boolean authentication(String id, String password) {
		System.out.println("���̵�/�н����� Ȯ��");
		return true;
	}

	@Override
	protected int authorization(String userName) {
		System.out.println("���� Ȯ��");
		return 0;
	}

	@Override
	protected String connection(String info) {
		System.out.println("������ ���� �ܰ�!");
		return info;
	}

}
