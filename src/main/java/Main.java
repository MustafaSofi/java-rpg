//day = 4
//hours = 9
//TODO: Add enemy rewards, Bring Healing Potions Back.
import java.util.Random;
import java.util.Scanner;

public class Main {


	public static int fight (int pHealth, int pStrength,int pArmor , String eName, int eStrength, int eHealth, Scanner scanner  ){

		int choice;
		int pBlock = 0;

		while (pHealth > 0 && eHealth > 0) {
			System.out.print("1. Attack\n2. Block\n3. Heal \nEnter a Number: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid number: ");
				scanner.next();

			}
			choice = scanner.nextInt();

			// reset Block 
			pBlock  = 0;

			if (choice == 1) 
				eHealth = attack(eHealth, pStrength, eName);

			else if (choice == 2) {
				pBlock = block(pArmor);
			}

			else if (choice == 3) {
				System.out.println("You chose to heal");
				if (pHealth>= 100){
					System.out.println("But your health is already full");
					continue;
				}else
					pHealth = heal(pHealth);
			}
			else{
				System.out.println("Please enter a number from 1 to 3");
				continue;
			}	

			if (eHealth > 0) 
				pHealth = enemyTurn(pHealth, pBlock, eName, eStrength);

			if (eHealth == 0)
				System.out.println(eName+" is Dead :)");
			else
				System.out.println("Be Careful, "+eName+ " is still standing!!");

			if (pHealth == 0) 

				System.out.println("You Died, Try Again.");	

		}
		eHealth = 90;
		return pHealth;
	}

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
		System.out.println("You Heal for 15 HP.\nYour Health is: "+health+" HP ");
		return health;

	}

	static int block (int armor){
		System.out.println("You chose to Block");
		int playerBlock = armor * 3; 
		System.out.println("You Blocked "+playerBlock+" hitpoints");
		return playerBlock;
	}

	static int attack (int enemyHealth, int pStrength, String enemy){

		Random random = new Random();

		int playerDamage = pStrength * random.nextInt(4, 8);
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
		int playerArmor = 4;
		int playerHealth = 100;
		int playerStrength = 3;
		//enemy
		int enemyHealth = 70;
		int enemyStrength = 3;
		String enemyName = "Goblin";
		System.out.println("A Goblin just came to you, what to do?");		

		playerHealth = fight(playerHealth, playerStrength, playerArmor, enemyName, enemyStrength, enemyHealth, scanner);

		if(playerHealth > 0){
			System.out.println("A Skeleton is here!!! What to do?");
			//Change enemy to skeleton 
			enemyHealth=90;
			enemyStrength = 4;
			enemyName = "Skeleton";
			playerHealth = fight(playerHealth, playerStrength, playerArmor, enemyName, enemyStrength, enemyHealth, scanner);
		}

		scanner.close();
	}

}
