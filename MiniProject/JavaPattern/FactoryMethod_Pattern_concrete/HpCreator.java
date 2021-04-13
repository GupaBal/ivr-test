package FactoryMethod_Pattern_concrete;

import java.util.Date;

import FactoryMethod_Pattern_framework.Item;
import FactoryMethod_Pattern_framework.ItemCreator;

public class HpCreator extends ItemCreator{

	@Override
	protected void requestItemsInfo() {
		System.out.println("�����ͺ��̽����� ü�� ȸ�� ������ ������ �����´�.");
		
	}

	@Override
	protected void createItemLog() {
		System.out.println("ü�� ȸ�� ������ ���� ���� �߽��ϴ�."+new Date());
		
	}

	@Override
	protected Item createItem() {
		//�۾�
		return new HpPotion();
	}
	

}
