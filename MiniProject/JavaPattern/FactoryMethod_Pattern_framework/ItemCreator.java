package FactoryMethod_Pattern_framework;

public abstract class ItemCreator {

	//create()가 팩토리 매소드이다. -> 팸플릿 매소드와 비슷 
	public Item create() {
		Item item;
		
		//step1 아이템 정보 가져오기
		requestItemsInfo();
		//step2 아이템 생성
		item = createItem();
		//step3 아이템 생성에 대한 로그
		createItemLog();
		return item;
	}

	//아이템을 생성하기 전에 데이터 베이스에서 아이템 정보를 요청한다. 
	abstract protected void requestItemsInfo();
	//아이템을 생성 후 아이템 복제 등의 불법을 방지하기 위해 데이터 베이스에 아이템 생성 정보를 남긴다.
	abstract protected void createItemLog();
	//아이템을 생성하는 알고리즘 
	abstract protected Item createItem();
}
