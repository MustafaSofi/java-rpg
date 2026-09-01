//day = 1
//hours = 1

import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int strength = 3;
		int armor = 3;
		int playerHealth = 100;
		int goblinHealth = 70;
		int playerDamage = strength * 5;
		int choice = 0;
		int healingPotion = 3;
		int playerBlock = armor * 3;
		int goblingDamage = strength * 4;
		int damageTaken = 0;
		System.out.println("A Goblin just came to you, what to do?");		


		while (playerHealth > 0) {

			System.out.print("1. Attack\n2. Block\n3.Heal \n Enter a Number: ");

			choice = scanner.nextInt();			


			if (choice == 1) {

				System.out.println("You attacked the goblin");
				goblinHealth -= playerDamage  ;
				System.out.println("You Damaged the Gobling for: "+playerDamage+" HP\n"+"Goblin health: "+goblinHealth);


			}
			else if (choice == 2) {

				System.out.println("You chose to Block");
				damageTaken = goblingDamage - playerBlock;
				playerHealth  -= damageTaken;
				System.out.println("You Blocked "+playerBlock+"hitpoints");
				System.out.println("Taken Damage: "+damageTaken+" HP\n"+"Your Health is: "+playerHealth);

			}


		System.out.println("Goblin is still standing!!!!");

		}





	}
}
