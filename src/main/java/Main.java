//day = 6
//hours = 12.5
import java.util.Random;
import java.util.Scanner;


public class Main {
	static int lightAttack (int enemyHealth, int pStrength, String enemy){

		Random random = new Random();
		int playerDamage = pStrength * random.nextInt(3, 7);
		int playerCritRoll = random.nextInt(1, 6);

		if (playerCritRoll == 3){
			System.out.println("Critical HIT!! x2 Damage");
			playerDamage *= 2;
		}

		System.out.println("You Light Attacked the "+enemy);
		enemyHealth =Math.max(0, enemyHealth - playerDamage) ;
		System.out.println("You Damaged the "+enemy+" for: "+playerDamage+" HP\n"+enemy+" health: "+enemyHealth);

		return enemyHealth;
	} 

	static int heavyAttack (int enemyHealth, int pStrength, String enemy){

		Random random = new Random();
		int missChance = random.nextInt(1, 5);
		int playerDamage = pStrength * random.nextInt(6, 11);
		int playerCritRoll = random.nextInt(1, 11);

		if (missChance == 1){
			System.out.println("You Missed ");
			return enemyHealth;

		}

		if (playerCritRoll == 3){
			System.out.println("Critical HIT!! x2 Damage");
			playerDamage *= 2;
		}

		System.out.println("You Heavy Attacked the "+enemy);
		enemyHealth =Math.max(0, enemyHealth - playerDamage) ;
		System.out.println("You Damaged the "+enemy+" for: "+playerDamage+" HP\n"+enemy+" health: "+enemyHealth);

		return enemyHealth;
	} 
	public static int[] fight (int pHealth, int pStrength,int pArmor, int potions, String eName, int eStrength, int eHealth, Scanner scanner  ){

		int choice;
		int attackChoice;
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

			switch (choice){
				case 1:
					System.out.print("1. Light Attack - lower but guaranteed damage\n2. Heavy Attack - high damage but have 25% miss chance\nChoose one: ");
					while (!scanner.hasNextInt()) {
						System.out.print("Please enter a valid number: ");
						scanner.next();

					}
					attackChoice = scanner.nextInt();
					switch (attackChoice) {
						case 1:
							eHealth = lightAttack(eHealth, pStrength, eName);
							break;
						case 2:
							eHealth = heavyAttack(eHealth, pStrength, eName);
							break;


						default:
							System.out.println("Please enter 1 or 2");
							continue;
					}
					break;
				case 2:
					pBlock = block(pArmor);
					break;
				case 3:
					System.out.println("You chose to heal");
					if (pHealth >= 100) {
						System.out.println("But your health is full");
						continue;
					}
					else if (potions <= 0) {
						System.out.println("You Dont have any Healing Potions left");
						continue;
					}
					else {
						pHealth = heal(pHealth);
						potions--;
						System.out.println("You have "+potions+" Healing Potions left");
					}
					break;

				default:
					System.out.println("Please Enter a number from 1 to 3");
					continue;

			}	
			if (eHealth > 0) 
				pHealth = enemyTurn(pHealth, pBlock, eName, eStrength);

			if (pHealth == 0){ 
				System.out.println("You Died, Try Again.");
				break;
			}
			if (eHealth == 0)
				System.out.println(eName+" is Dead :)");
			else
				System.out.println("Be Careful, "+eName+ " is still standing!!");



		}
		return new int[] {pHealth, potions};
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

		health =Math.min(100, health+35);
		System.out.println("You Heal for 35 HP.\nYour Health is: "+health+" HP ");
		return health;

	}

	static int block (int armor){
		System.out.println("You chose to Block");
		int playerBlock = armor * 3; 
		System.out.println("You Blocked "+playerBlock+" hitpoints");
		return playerBlock;
	}

	public static void main(String[] args){

		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		//player 
		int playerArmor = 4;
		int playerHealth = 100;
		int playerStrength = 3;
		int healingPotions = 3;
		int[] result;
		//Enemy
		int[] enemyHealth = {70, 80, 110,};
		int[] enemyStrength = {3, 3, 4};
		String[] enemyNames = {"Goblin", "Skeleton", "Orc" };

		for ( int i = 0 ; i < enemyNames.length ; i++ ){


			System.out.println("A "+enemyNames[i]+" just came to you, what to do? ( "+enemyHealth[i]+" HP - "+enemyStrength[i]+" Strength )");		


			result = fight(playerHealth, playerStrength, playerArmor, healingPotions, enemyNames[i], enemyStrength[i], enemyHealth[i], scanner);
			playerHealth = result[0];
			healingPotions = result[1];

			if (playerHealth <= 0)
				break;

			System.out.println("the "+enemyNames[i]+" dropped something!!!!");
			int rewardRoll = random.nextInt(1, 4); 

			if (rewardRoll == 1 ) {
				System.out.println("+25HP !!!!");
				playerHealth =Math.min(100, playerHealth + 25);	
			} else if(rewardRoll == 2){
				System.out.println("+2 Armor !!");
				playerArmor += 2;
			} else if(rewardRoll == 3){
				System.out.println("+2 Strength !!");
				playerStrength += 2;
			}

		}
		if (playerHealth > 0) 
			System.out.println("Congrats! You won the run of this easy game :)");


		scanner.close();
	}

}
