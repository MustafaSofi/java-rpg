//day = 3
//hours = 6

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static int goblinTurn(int playerHP, int block){
		
		Random random = new Random();
		int goblinStrength = 3;
		int goblinDamage = goblinStrength * random.nextInt(4, 8);
		int goblinCritRoll = random.nextInt(1, 11);
		if (goblinCritRoll == 2){
			System.out.println("Critical HIT!! x2 Goblin Damage");
			goblinDamage *=2;
		}
		int damageTaken = Math.max(0, goblinDamage - block);
		System.out.println("Goblin turn: He hit you with "+damageTaken+" HP");
		playerHP = Math.max(0 , playerHP - damageTaken);
		System.out.println("Your health is: "+playerHP);
		return playerHP;
	}




	static int heal (int potions ,int health){

		health =Math.min(100, health+15);
		System.out.println("You Heal for 15 HP.\n-1 Healing Potion.\nYour Health is: "+health+" HP ");
		return health;

	}



	static int block (int armor){
		System.out.println("You chose to Block");
		int playerBlock = armor * 3; 
		System.out.println("You Blocked "+playerBlock+" hitpoints");
		return playerBlock;
	}

	static int attack (int goblinHealth){

		Random random = new Random();

		int playerStrength = 3;
		int playerDamage = playerStrength * random.nextInt(4, 8);
		int playerCritRoll = random.nextInt(1, 11);


		if (playerCritRoll == 3){
			System.out.println("Critical HIT!! x2 Damage");
			playerDamage *= 2;
		}


		System.out.println("You attacked the goblin");
		goblinHealth =Math.max(0, goblinHealth - playerDamage) ;
		System.out.println("You Damaged the Goblin for: "+playerDamage+" HP\n"+"Goblin health: "+goblinHealth);

		return goblinHealth;
	}  




	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int playerBlock = 0;
		int armor = 4;
		int playerHealth = 100;
		int choice = 0;
		int healingPotion = 3;
		int goblinHealth = 70;

		System.out.println("A Goblin just came to you, what to do?");		


		while (playerHealth > 0 && goblinHealth > 0) {
			System.out.print("1. Attack\n2. Block\n3. Heal \n Enter a Number: ");
			choice = scanner.nextInt();			
			//Calculate Random damage (12 - 21 )
			// reset Block
			playerBlock = 0;

			if (choice == 1) 
				goblinHealth = attack(goblinHealth);

			else if (choice == 2) {
				playerBlock = block(armor);
			}

			else if (choice == 3) {
				System.out.println("You chose to heal");
				if (playerHealth>= 100){
					System.out.println("But your health is already full");
					continue;
				}
				else if (healingPotion <= 0){ 
					System.out.println("But you have no healing potions left");
					continue;
				}else{
				healingPotion--;
				playerHealth = heal(healingPotion,playerHealth);
				System.out.println("You have "+healingPotion+" Healing Potions left");

				}
		
			}


			else{

				System.out.println("Please enter a number from 1 to 3");
				continue;

			}	
			
			if (goblinHealth > 0) 
			playerHealth = goblinTurn(playerHealth, playerBlock);

			if (goblinHealth == 0)
				System.out.println("You win!!, Goblin is Dead :)");
			else
				System.out.println("Be Careful, Goblin is still standing!!");

			if (playerHealth == 0) 

				System.out.println("You Died, Try Again.");	


		}





	}
}
