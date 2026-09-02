//day = 2
//hours = 4.5

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();


		int playerBlock = 0;
		int strength = 3;
		int armor = 3;
		int playerHealth = 100;
		int goblinHealth = 70;
		int playerDamage = 0; 
		int choice = 0;
		int healingPotion = 3;
		int goblinDamage = 0;
		int damageTaken =0 ;
		int crit = 0;

		System.out.println("A Goblin just came to you, what to do?");		


		while (playerHealth > 0 && goblinHealth > 0) {

			System.out.print("1. Attack\n2. Block\n3. Heal \n Enter a Number: ");

			choice = scanner.nextInt();			

			// 1 in a 10 chance to get crit
			crit = random.nextInt(1, 11);
			
			//Calculate Random damage (12 - 21 )
			goblinDamage = strength * random.nextInt(4, 8);
			playerDamage = strength * random.nextInt(4, 8);
			// reset Block
			playerBlock = 0;

			if (crit == 3)
				playerDamage *=2;

			if (crit == 2)
				goblinDamage *=2;


			damageTaken = Math.max(0, goblinDamage - playerBlock);


			if (choice == 1) {

				if (crit == 3)
					System.out.println("Critical HIT!! x2 Damage");

				System.out.println("You attacked the goblin");
				goblinHealth =Math.max(0, goblinHealth - playerDamage) ;
				System.out.println("You Damaged the Goblin for: "+playerDamage+" HP\n"+"Goblin health: "+goblinHealth);


			}
			else if (choice == 2) {

				System.out.println("You chose to Block");
				playerBlock = armor * 3; 
				System.out.println("You Blocked "+playerBlock+" hitpoints");
				damageTaken =Math.max(0, goblinDamage - playerBlock);
			}

			else if (choice == 3) {
				System.out.println("You chose to heal");
				if (playerHealth>= 100){
					System.out.println("Your health is already full");
					continue;
				}
				else if (healingPotion <= 0){ 
					System.out.println("you have no healing potions");
					continue;
				}
				else{
					healingPotion--;
					playerHealth =Math.min(100, playerHealth+15);

					System.out.println("You Heal for 15 HP -1 Healing Potion\nYou have "+healingPotion+" Healing Potions left.\nYour Health is: "+playerHealth+" HP ");
				}
			}else{

				System.out.println("Please enter a number from 1 to 3");
				continue;

			}	


			if (goblinHealth > 0) {

				if (crit == 2)
					System.out.println("Critical HIT!! x2 Goblin Damage");

				System.out.println("Goblin turn: He hit you with "+damageTaken+" HP");
				playerHealth = Math.max(0 , playerHealth - damageTaken);
				System.out.println("Your health is: "+playerHealth);
			}

			if (goblinHealth == 0)
				System.out.println("You win!!, Goblin is Dead :)");
			else
				System.out.println("Be Careful, Goblin is still standing!!");

			if (playerHealth == 0) 

				System.out.println("You Died, Try Again.");	


		}





	}
}
