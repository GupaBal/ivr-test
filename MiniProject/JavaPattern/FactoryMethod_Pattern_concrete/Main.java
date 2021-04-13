package FactoryMethod_Pattern_concrete;

import FactoryMethod_Pattern_framework.Item;
import FactoryMethod_Pattern_framework.ItemCreator;

public class Main {

	public static void main(String[] args) {

		ItemCreator creator;
		Item item;
		
		creator = new HpCreator();
		item = creator.create();

		item.use();

		creator = new MpCreator();
		item = creator.create();
		item.use();
	}

}
