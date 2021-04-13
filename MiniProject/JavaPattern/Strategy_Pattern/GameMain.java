package Strategy_Pattern;

public class GameMain {

	public static void main(String[] args) {

		GameCharacter character = new GameCharacter();
		
		character.attack(); 
		character.setWeapon(new Knife());
		character.attack(); 
		character.setWeapon(new Sword());
		character.attack(); 
		character.setWeapon(new Ax());
		character.attack(); 
		character.attack(); 
		character.attack(); 
		character.attack(); 
	}

}
