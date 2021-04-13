package FactoryMethod_Pattern_concrete;

import java.util.Date;

import FactoryMethod_Pattern_framework.Item;
import FactoryMethod_Pattern_framework.ItemCreator;

public class MpCreator extends ItemCreator{

	@Override
	protected void requestItemsInfo() {
		System.out.println("데이터베이스에서 마력 회복 물약의 정보를 가져온다.");
		
	}

	@Override
	protected void createItemLog() {
		System.out.println("마력 회복 물약을 새로 생성 했습니다."+new Date());
		
	}

	@Override
	protected Item createItem() {
		//작업
		return new MpPotion();
	}
	

}
