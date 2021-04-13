package FactoryMethod_Pattern_framework;

public abstract class ItemCreator {

	//create()�� ���丮 �żҵ��̴�. -> ���ø� �żҵ�� ��� 
	public Item create() {
		Item item;
		
		//step1 ������ ���� ��������
		requestItemsInfo();
		//step2 ������ ����
		item = createItem();
		//step3 ������ ������ ���� �α�
		createItemLog();
		return item;
	}

	//�������� �����ϱ� ���� ������ ���̽����� ������ ������ ��û�Ѵ�. 
	abstract protected void requestItemsInfo();
	//�������� ���� �� ������ ���� ���� �ҹ��� �����ϱ� ���� ������ ���̽��� ������ ���� ������ �����.
	abstract protected void createItemLog();
	//�������� �����ϴ� �˰��� 
	abstract protected Item createItem();
}
