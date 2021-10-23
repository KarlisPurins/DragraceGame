import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int money = 10000;

		// int menuInputValue = 0;
		Scanner menuInput = new Scanner(System.in);
		//Car car1 = new Car(180, 8.2, "Volksvâgens", 2001);
		//Car car2 = new Car(155, 7.7, "Volksvâgens123", 3510);
		

		boolean play = true;
		while (play) {
			System.out.println();
			System.out.println("Write number in -=console=- for choosen category");
			System.out.println("1 -> Garage");
			System.out.println("2 -> Marketplace");
			System.out.println("3 -> Race");
			System.out.println("4 -> Exit");
			System.out.println();
			int scannedValue = menuInput.nextInt();

			switch (scannedValue) {
			case 1:
				System.out.println();
				System.out.println("Garage");
				break;
			case 2:
				System.out.println();
				System.out.println("Marketplace");
				break;
			case 3:
				System.out.println();
				System.out.println("Race");
				//Racing._800mRace(car1, car2);
				break;
			case 4:
				System.out.println();
				System.out.println("Exit");
				play = false;
				break;
			default:
				break;
			}
		}
		menuInput.close();
	}

}
