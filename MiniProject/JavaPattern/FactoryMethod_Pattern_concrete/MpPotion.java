package FactoryMethod_Pattern_concrete;

import FactoryMethod_Pattern_framework.Item;

public class MpPotion implements Item{

	@Override
	public void use() {
			System.out.println("마력 회복!!");		
	}

	
}
