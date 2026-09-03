//day = 3
//hours = 8

import java.util.Random;
import java.util.Scanner;

public class Main {

	static int enemyTurn(int playerHP, int block, String enemy, int strength){

		Random random = new Random();
		int enemyCritRoll = random.nextInt(1, 11);
		int enemyDamage = strength * random.nextInt(4, 8);
		if (enemyCritRoll == 2){
			System.out.println("Critical HIT!! x2 "+enemy+" Damage");
			enemyDamage*=2;
		}
		int damageTaken = Math.max(0, enemyDamage - block);
		System.out.println(enemy+" turn: He hit you with "+damageTaken+" HP");
		playerHP = Math.max(0 , playerHP - damageTaken);
		System.out.println("Your health is: "+playerHP);
		return playerHP;
	}

	static int heal (int health){

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

	static int attack (int enemyHealth, String enemy){

		Random random = new Random();

		int playerStrength = 3;
		int playerDamage = playerStrength * random.nextInt(4, 8);
		int playerCritRoll = random.nextInt(1, 11);

		if (playerCritRoll == 3){
			System.out.println("Critical HIT!! x2 Damage");
			playerDamage *= 2;
		}

		System.out.println("You attacked the "+enemy);
		enemyHealth =Math.max(0, enemyHealth - playerDamage) ;
		System.out.println("You Damaged the "+enemy+" for: "+playerDamage+" HP\n"+enemy+" health: "+enemyHealth);

		return enemyHealth;
	}  

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		//player 
		int playerBlock = 0;
		int armor = 4;
		int playerHealth = 100;
		int choice = 0;
		int healingPotion = 3;

		//enemy
		int enemyHealth = 70;
		int enemyStrength = 3;
		String enemyName = "Goblin";
		System.out.println("A Goblin just came to you, what to do?");		

		while (playerHealth > 0 && enemyHealth > 0) {
			System.out.print("1. Attack\n2. Block\n3. Heal \nEnter a Number: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid number: ");
				scanner.next();

			}

			choice = scanner.nextInt();
			//Calculate Random damage (12 - 21 )
			// reset Block
			playerBlock = 0;

			if (choice == 1) 
				enemyHealth = attack(enemyHealth, enemyName);

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
					playerHealth = heal(playerHealth);
					System.out.println("You have "+healingPotion+" Healing Potions left");

				}

			}

			else{

				System.out.println("Please enter a number from 1 to 3");
				continue;

			}	

			if (enemyHealth > 0) 
				playerHealth = enemyTurn(playerHealth, playerBlock, enemyName, enemyStrength);

			if (enemyHealth == 0)
				System.out.println(enemyName+" is Dead :)");
			else
				System.out.println("Be Careful, "+enemyName+ " is still standing!!");

			if (playerHealth == 0) 

				System.out.println("You Died, Try Again.");	

		}

		//Change enemy to skeleton 
		enemyHealth = 90;
		enemyStrength = 4;
		enemyName = "Skeleton";

		if(playerHealth !=0){
			System.out.println("A Skeleton is here!!! What to do?");

			while (playerHealth > 0 && enemyHealth > 0) {
				System.out.print("1. Attack\n2. Block\n3. Heal \nEnter a Number: ");

				while (!scanner.hasNextInt()) {
					System.out.print("Please enter a valid number: ");
					scanner.next();
				}

				choice = scanner.nextInt();
				//Calculate Random damage (12 - 21 )
				// reset Block
				playerBlock = 0;

				if (choice == 1) 
					enemyHealth = attack(enemyHealth, enemyName);

				else if (choice == 2) 
					playerBlock = block(armor);
			

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
						playerHealth = heal(playerHealth);
						System.out.println("You have "+healingPotion+" Healing Potions left");

					}

				}

				else{
					System.out.println("Please enter a number from 1 to 3");
					continue;
				}	

				if (enemyHealth > 0) 
					playerHealth = enemyTurn(playerHealth, playerBlock, enemyName, enemyStrength);

				if (enemyHealth == 0)
					System.out.println(enemyName+" is Dead :)");
				else
					System.out.println("Be Careful, "+enemyName+ " is still standing!!");

				if (playerHealth == 0) 
					System.out.println("You Died, Try Again.");	

			}

		}

	}

}
