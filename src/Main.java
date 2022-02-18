import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Main {
	
	
	static ArrayList<Car> carsInGarage = new ArrayList<Car>();
	static int money = 10000;
	
	
	public static void main(String[] args) {
		
		
		int _RaceDifficultyLevel;
		int _NumberOfOWnedCars = 2;
		Scanner menuInput = new Scanner(System.in);
		Random rnd = new Random ();
		
		
		
		boolean play = true;
		
		while (play) {
			
			System.out.println();
			System.out.println("Write number in -=console=- for choosen category");
			System.out.println("1 -> Garage");
			System.out.println("2 -> Marketplace");
			System.out.println("3 -> Race");
			System.out.println("0 -> Exit");
			System.out.println();
			int scannedValue = menuInput.nextInt();

			switch (scannedValue) {
			case 1:	//Garage menu
				garageShowCars();
				scannedValue = menuInput.nextInt();
				switch (scannedValue) {
				case 1:
					break;
				default:
					break;
				}
				break;
			case 2:	//Marketplace menu
				Car car2 = new Car("Subaru", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
				Car car3 = new Car("Volvo", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
				Car car4 = new Car("AUDI", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
				_MarketplaceBeforeAviableCarList();
				System.out.println("1 " + car2.toString());
				System.out.println("2 " + car3.toString());
				System.out.println("3 " + car4.toString());
				_MarketplaceAfterAviableCarList(); 
				scannedValue = menuInput.nextInt();
				switch (scannedValue) {
				case 1:
					buyCar(car2);
					break;
				case 2:
					buyCar(car3);
					break;
				case 3:
					buyCar(car4);
					break;
				case 4:
					break;
				default:
					break;
				}
				break;
			case 3:	//Race menu
				_RaceMenu();
				int levelSelectValue = menuInput.nextInt(); //Value for level
				boolean _RaceMenuPlay = true;
				if(levelSelectValue >= 1) {
					while (_RaceMenuPlay) {
						 //level value for math to calculate BET size
						level(levelSelectValue);
						int levelSelectedValue = menuInput.nextInt();
						if(levelSelectedValue >= 1) {
							_RaceBETs(scannedValue, levelSelectedValue);
							scannedValue = menuInput.nextInt();
						}
						if(scannedValue == 0) {
							_RaceMenuPlay = false;
						}
					}
				}else //input to go back to Main Menu
					break;
				
			case 4:
				System.out.println();
				System.out.println("0 -> Exit");
				play = false;
				break;
			default:
				break;
			}
		}
		menuInput.close();
	}
	
	
	
	public static void garageShowCars() {
		_GarageBeforeOwnedCarList();
		if(carsInGarage.size() != 0) {
			int i=1;
			for(Car car: carsInGarage) {
				car.printInGarage(i);
				i++;
			}
		}
		_GarageAfterOwnedCarList(); 
	}
	
	public static void _GarageBeforeOwnedCarList() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Garage");
		System.out.println("-----------------------------------------");
		System.out.println("#	Name	Acc	Speed	Price");
		System.out.println();
	}
	public static void _GarageAfterOwnedCarList() {
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back");
		System.out.println();
	}
	
	public static void _MarketplaceBeforeAviableCarList() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Marketplace");
		System.out.println("-----------------------------------------");
		System.out.println("#	Name	Acc	Speed	Price");
		System.out.println();
	}
	public static void _MarketplaceAfterAviableCarList() {
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back");
		System.out.println();
	}
	
	private static void buyCar(Car car) {
		if(money >= car.cost) {
			System.out.println("Purchase successful! :)");
			car.cost /= 2;
			carsInGarage.add(car);
			money -= car.cost;
		}else {
			System.out.println("Not enough money... :(");
		}
	}
	
	private static void sellCar(int index) {
		carsInGarage.remove(index);
	}
	
	public static void _RaceMenu() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Race");
		System.out.println("-----------------------------------------");
		System.out.println("Choose race dificulty level (1 to 5 -> from easier to more dificult)");
		System.out.println("-----------------------------------------");
		System.out.println("1 -> Level 1");
		System.out.println("2 -> Level 2");
		System.out.println("3 -> Level 3");
		System.out.println("4 -> Level 4");
		System.out.println("5 -> Level 5");
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back to Main Menu");
		System.out.println();
		
	}
	
	public static void level(int chosenLevel) {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Level " + chosenLevel);
		System.out.println("-----------------------------------------");
		System.out.println("		Choose race type");
		System.out.println("1 -> 1/2 mile race");
		System.out.println("2 -> 1/4 mile race");
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back to Level Menu");
		System.out.println();
		
	}
	
	public static void _RaceBETs(int _RaceType, int _DifficultyCoefficient) {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		1/" + _RaceType*2 + " mile race");
		System.out.println("-----------------------------------------");
		System.out.println("		Choose Your BET");
		for (int i = 1; i < 5; i++) {
			System.out.println(i + " -> " + Math.round(i*200*_DifficultyCoefficient*0.69) + " $");
		}
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back");
		System.out.println();
	}
	
}