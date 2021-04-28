package test;

public class CarTest {

	String color;
	String gearType;
	int door;
	
	public CarTest() {
		this("white","auto",4);
	}

	public CarTest(CarTest c) {
		color = c.color;
		gearType = c.gearType;
		door = c.door;
	}
	
	public CarTest(String color, String gearType, int door) {
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
	public static void main(String[] args) {
		
		CarTest c1 = new CarTest();
		CarTest c2 = new CarTest(c1);		//	c1 �� ���纻�� c2�� �ִ´� 
		
		
		System.out.println("c1�� color +"+ c1.color +"gearType"+c1.gearType+"door"+c1.door);
		System.out.println("c2�� color +"+ c2.color +"gearType"+c2.gearType+"door"+c2.door);
		c1.door = 10000;
		System.out.println("c1�� color +"+ c1.color +"gearType"+c1.gearType+"door"+c1.door);
		System.out.println("c2�� color +"+ c2.color +"gearType"+c2.gearType+"door"+c2.door);
	}

}
