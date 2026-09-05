public class testing {

	public static void main(String[] args){

		String[] names = {"Goblin", "Skeleton", "Orc"};
		int[] health = {70, 80, 110,};
		int[] strength = {3, 3, 4};	


		for (int i = 0 ; i < names.length  ; i++ ){
			System.out.println(names[i]+" - "+health[i]+" HP - "+strength[i]+" Strength");

		}

	}
}
