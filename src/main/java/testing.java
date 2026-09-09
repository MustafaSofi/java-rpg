class Player {
	String name;
	int health; 
	int strength;
	int armor;
}

public class testing {

	public static void main(String[] args){
		Player warrior = new Player();	
	
		warrior.name = "Warrior";
		warrior.health = 150;
		warrior.strength = 2;
		warrior.armor = 6;

		Player player = new Player();
		player.name = "Player";
		player.health = 100;
		player.armor = 3;
		player.strength = 3;

		System.out.println(player.name+"\nHealth "+player.health+"\nStrength "+player.strength+"\nArmor "+player.armor);
		System.out.println(warrior.name+"\nHealth "+warrior.health+"\nStrength "+warrior.strength+"\nArmor "+warrior.armor);
	}
}
