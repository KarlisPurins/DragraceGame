import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Main {
	
	
	static ArrayList<Car> carsInGarage = new ArrayList<Car>();
	static ArrayList<Car> carsInMarketplace = new ArrayList<Car>();
	static int money = 5000;
	
	
	public static void main(String[] args) {
		
		int _RaceDifficultyLevel;
		int _NumberOfOWnedCars = 2;
		Scanner menuInput = new Scanner(System.in);
		Random rnd = new Random ();
		
		startupUI();
		
		boolean play = true;
		while (play) {
			if(money <= 0 && carsInGarage.size() == 0) {
				System.out.println("You lost the game...");
				System.out.println("Good luck next time! :)");
				break;
			}
			if(money >= 10000) {
				System.out.println("You won the game...");
				System.out.println("You must do it again! :|");
				System.out.println("Good luck! :)");
				break;
			}
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
				if(carsInGarage.size() == 0) {
					System.out.println("You have no cars to race with... :(");
					break;
				}
				Car selectedCar = selectCarForRacing(menuInput);
				if(selectedCar.name == "noname") {
					break;
				}
				_RaceMenuUI();
				int levelSelectValue = menuInput.nextInt(); //Value for level
				if(levelSelectValue >= 1) { //Selects level
					boolean _RaceMenuPlay = true;
					while (_RaceMenuPlay) {
						 //level value for math to calculate BET size
						levelUI(levelSelectValue);
						int raceTypeSelect = menuInput.nextInt();
						if(raceTypeSelect >= 1) {
							int[] betArray = _RaceBETs(raceTypeSelect, levelSelectValue);
							int betSelectedValue = menuInput.nextInt();
							if(betSelectedValue == 0) { //Check if pressed "Go back" while selecting BET
								continue;
							}
							while(betArray[betSelectedValue-1] > money) {
								System.out.println("Not enough money... Choose diferent bet or go back");
								betSelectedValue = menuInput.nextInt();
								if(betSelectedValue == 0) {
									break;
								}
								System.out.println("Not enough money...");
								betArray = _RaceBETs(raceTypeSelect, levelSelectValue);
							}
							if(betSelectedValue == 0) { //Check if pressed "Go back" while selecting BET
								continue;
							}
							if(raceTypeSelect == 1) {
								if(Racing._800mRace(selectedCar, createEnemyCar(levelSelectValue, rnd))) {
									money += betArray[betSelectedValue-1];
								}else {
									money -= betArray[betSelectedValue-1];
								}
							}else {
								if(Racing._400mRace(selectedCar, createEnemyCar(levelSelectValue, rnd))){
									money += betArray[betSelectedValue-1];
								}else {
									money -= betArray[betSelectedValue-1];
								}
							}
							
						}else {
							_RaceMenuPlay = false;
							//levelSelectedValue = 0;
						}
					}
				}else {
					continue;
				}
				break;
			case 9: //For testing purposes
				money -= 100;
				break;
			case 99: //For testing purposes
				money -= 1000;
				break;
			case 90: //For testing purposes
				money += 1000;
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
	
	private static Car createEnemyCar(int levelSelectValue, Random rnd) {
		//Future: Enemy car power is different for each level selected
		return new Car("Subaru", Math.round((rnd.nextDouble()*10+7)*10.0)/10.0, rnd.nextInt(30)+150, rnd.nextInt(2000)+2000);
	}

	private static Car selectCarForRacing(Scanner input) {
		Car selectedCar;
		System.out.println("Select car for racing:");
		int i=1;
		for(Car car: carsInGarage) {
			car.printInGarage(i);
			i++;
		}
		System.out.println("0 -> Back");
		int inputInt = input.nextInt();
		if(inputInt == 0) {
			return new Car("noname", 0.0, 0, 0);
		}
		while(inputInt < 0 || inputInt > carsInGarage.size()) {
			System.out.println("Wrong input");
			inputInt = input.nextInt();
			if(inputInt == 0) {
				return new Car("noname", 0.0, 0, 0);
			}
		}
		selectedCar = carsInGarage.get(inputInt-1);
		return selectedCar;
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
	
	private static void garageShowCars() {
		_GarageBeforeOwnedCarList();
		if(carsInGarage.size() != 0) {
			int i=1;
			for(Car car: carsInGarage) {
				car.printInGarage(i);
				i++;
			}
		}else {
			System.out.println("No cars in garage...");
		}
		_GarageAfterOwnedCarList(); 
	}
	
	private static void _GarageBeforeOwnedCarList() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Garage");
		System.out.println("-----------------------------------------");
		System.out.println("#	Name	Acc	Speed	Price");
		System.out.println();
	}
	private static void _GarageAfterOwnedCarList() {
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
	
	
	private static void _MarketplaceBeforeAviableCarList() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Marketplace");
		System.out.println("-----------------------------------------");
		System.out.println("#	Name	Acc	Speed	Price");
		System.out.println();
	}
	private static void _MarketplaceAfterAviableCarList() {
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
	
	private static void _RaceMenuUI() {
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
	
	private static void levelUI(int chosenLevel) {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		Level " + chosenLevel);
		System.out.println("-----------------------------------------");
		System.out.println("		Choose race type");
		System.out.println("1 -> 1/2 mile race");
		System.out.println("2 -> 1/4 mile race");
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back to Main Menu");
		System.out.println();
		
	}
	
	private static int[] _RaceBETs(int _RaceType, int _DifficultyCoefficient) {
		int[] betArray = new int[5];
		System.out.println();
		System.out.println("=========================================");
		System.out.println("		1/" + _RaceType*2 + " mile race");
		System.out.println("-----------------------------------------");
		System.out.println("		Choose Your BET");
		for (int i = 0; i < 5; i++) {
			int bet = (int) Math.round(i*200*_DifficultyCoefficient*0.69);
			betArray[i] = bet;
			System.out.println((i+1) + " -> " + bet + " $");
		}
		System.out.println("-----------------------------------------");
		System.out.println("0 -> Back");
		System.out.println();
		return betArray;
	}
	
	private static void startupUI(){
		System.out.println("Welcome to Dragrace Game where everything is possible (not everything, just dragracing)");
		System.out.println("In this wonderous world you have only one target:");
		System.out.println("You must get 10000 money to win this game.");
		System.out.println("Lost all money and cars? You lose.");
		System.out.println("In Garage you can see all your cars and you can sell them for minor money.");
		System.out.println("In Marketplace you shall buy your dream car which will take you to the moon and back. ;)");
		System.out.println("In Race you choose a car to race with, then difficulty level (bigger difficulty = bigger yields)");
		System.out.println("After Level you choose Race type - 1/2 mile or 1/4 mile");
		System.out.println("Finally you choose a bet you want to put on your victory and hope for the best RNG luck you could ever have.");
		System.out.println("Good luck, have fun! <3");
	}
	
}