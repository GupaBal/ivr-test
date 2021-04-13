package TemplateMethod_Pattern;

public abstract class AbstGameConnectHelper {
	
	//�ܺο����� ȣ���� �Ǹ� �ȵǴµ� ���� Ŭ���������� ���Ǿ�� ��. protected�� 
	protected abstract String doSecurity(String stirng);
	protected abstract boolean authentication(String id,String password);
	protected abstract int authorization(String userName);
	protected abstract String connection(String info);
	
	// ���ø� �޼ҵ� (������ ������)
	public String requestConnection(String encodedInfo) {
		
		//���� �۾� -> ��ȣȭ �� ���ڿ��� ��ȣȭ ����.
		String decodedInfo = doSecurity(encodedInfo);
		
		//��ȯ�� ���� ������, ���̵�,�н����带 �Ҵ��Ѵ�. 
		String id = "modern";
		String password = "1234";
		
		
		//���� ���� -> ���ѵ� ���̵�� �н������ ����
		if(!authentication(id, password)) {
			System.out.println("���̵� �� ��ȣ ����ġ");
			throw new Error("���̵� �� ��ȣ ����ġ");
		}
		
		//���� ���� ->
		String userName = "joshua";
		int i = authorization(userName);
		
		switch(i) {
		case 0: //���� �Ŵ���
			System.out.println("���� �Ŵ���");
			break;
		case 1: //����ȸ��
			System.out.println("����ȸ��");
			break;
		case 2: //����ȸ��
			System.out.println("����ȸ��");
			break;
		case 4: //��ȯ����
			System.out.println("��ȯ����");
			break;
		default: // ��Ÿ����
			System.out.println("��Ÿ����");
			break;
		}
		
		return connection(decodedInfo);
	}
	
}
