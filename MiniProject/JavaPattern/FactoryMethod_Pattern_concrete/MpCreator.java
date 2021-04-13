package FactoryMethod_Pattern_concrete;

import java.util.Date;

import FactoryMethod_Pattern_framework.Item;
import FactoryMethod_Pattern_framework.ItemCreator;

public class MpCreator extends ItemCreator{

	@Override
	protected void requestItemsInfo() {
		System.out.println("�����ͺ��̽����� ���� ȸ�� ������ ������ �����´�.");
		
	}

	@Override
	protected void createItemLog() {
		System.out.println("���� ȸ�� ������ ���� ���� �߽��ϴ�."+new Date());
		
	}

	@Override
	protected Item createItem() {
		//�۾�
		return new MpPotion();
	}
	

}
