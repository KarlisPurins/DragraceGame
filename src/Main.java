import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Main {
	
	
	static ArrayList<Car> carsInGarage = new ArrayList<Car>();
	static ArrayList<Car> carsInMarketplace = new ArrayList<Car>();
	static int money = 10000;
	
	
	public static void main(String[] args) {
		
		
		int _RaceDifficultyLevel;
		int _NumberOfOWnedCars = 2;
		Scanner menuInput = new Scanner(System.in);
		Random rnd = new Random ();
		
		
		
		boolean play = true;
		
		while (play) {
			mainMenuUI();
			
			int scannedValue = menuInput.nextInt();

			switch (scannedValue) {
			case 1:	//Garage menu
				garageShowCars();
				scannedValue = menuInput.nextInt();
				if(scannedValue == 0) {
					continue; //Go back to MainMenu
				}else {
					inGarageChosenCarMenu(menuInput, scannedValue); //do stuff with chosen car
				}
				break;
			case 2:	//Marketplace menu
				createCars(rnd);
				_MarketplaceBeforeAviableCarList();
				for(int i=0; i<carsInMarketplace.size(); i++) { //Show all cars in Marketplace
					System.out.println(i+1+ " " + carsInMarketplace.get(i).toString());
				}
				_MarketplaceAfterAviableCarList(); 
				scannedValue = menuInput.nextInt();
				switch (scannedValue) {
				case 1:
					buyCar(carsInMarketplace.get(0));
					carsInMarketplace.clear();
					break;
				case 2:
					buyCar(carsInMarketplace.get(1));
					carsInMarketplace.clear();
					break;
				case 3:
					buyCar(carsInMarketplace.get(2));
					carsInMarketplace.clear();
					break;
				default:
					carsInMarketplace.clear();
					break;
				}
				break;
			case 3:	//Race menu
				_RaceMenuUI();
				int levelSelectValue = menuInput.nextInt(); //Value for level
				if(levelSelectValue >= 1) { //Selects level
					boolean _RaceMenuPlay = true;
					while (_RaceMenuPlay) {
						 //level value for math to calculate BET size
						levelUI(levelSelectValue);
						int raceTypeSelect = menuInput.nextInt();
						if(raceTypeSelect >= 1) {
							_RaceBETs(scannedValue, levelSelectValue);
							scannedValue = menuInput.nextInt();
						}else {
							_RaceMenuPlay = false;
							//levelSelectedValue = 0;
						}
					}
				}else //input to go back to Main Menu
					break;
				
			case 0:
				System.out.println();
				System.out.println("Thank You for playing!");
				System.out.println("Goodbye!");
				play = false;
				break;
			default:
				break;
			}
		}
		menuInput.close();
	}
	
	private static void mainMenuUI() {
		System.out.println();
		System.out.println("Write number in -=console=- for choosen category");
		System.out.println("Your money: " + money);
		System.out.println("1 -> Garage");
		System.out.println("2 -> Marketplace");
		System.out.println("3 -> Race");
		System.out.println("0 -> Exit Game");
		System.out.println();
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
	
	private static void inGarageChosenCarMenu(Scanner scan, int givenInput) {
		System.out.println("Selected car:");
		System.out.println("#	Name	Acc	Speed	Price");
		carsInGarage.get(givenInput-1).printInGarage(givenInput);
		System.out.println();
		System.out.println("1 -> Sell it");
		System.out.println("0 -> Back");
		int scannedValue = scan.nextInt();
		if(scannedValue == 1) {
			sellCar(givenInput-1);
		}
	}
	
	private static void createCars(Random rnd) {
		Car car1 = new Car("Subaru", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
		Car car2 = new Car("Volvo", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
		Car car3 = new Car("AUDI", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
		carsInMarketplace.add(car1);
		carsInMarketplace.add(car2);
		carsInMarketplace.add(car3);
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
		System.out.println("Enter number of car to buy it");
		System.out.println("0 -> Back");
		System.out.println();
	}
	
	
	private static void buyCar(Car car) {
		if(money >= car.cost) {
			System.out.println("Purchase successful! :)");
			money -= car.cost;
			car.costReductionAfterBuying();
			carsInGarage.add(car);
		}else {
			System.out.println("Not enough money... :(");
		}
	}
	
	private static void sellCar(int index) {
		money += carsInGarage.get(index).cost;
		carsInGarage.remove(index);
	}
	
	public static void _RaceMenuUI() {
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
	
	public static void levelUI(int chosenLevel) {
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