package Strategy_Pattern;

public class GameCharacter {
	
	//������
	public Weapon weapon;
	
	//��ȯ ���� 
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void attack() {
		
		if(weapon == null) {
			System.out.println("�Ǽ� ����");
		}else {
			//��������Ʈ 
			weapon.attack();
		}
		
	}
}
