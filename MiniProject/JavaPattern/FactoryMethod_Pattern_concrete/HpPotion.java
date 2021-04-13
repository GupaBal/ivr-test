package FactoryMethod_Pattern_concrete;

import FactoryMethod_Pattern_framework.Item;

public class HpPotion implements Item{

	@Override
	public void use() {
		System.out.println("체력 회복!!");
		
	}

}
