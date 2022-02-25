
public class Racing {

	
	static boolean _400mRace(Car a, Car b) {
		if(isPlayerWinner(a, b, 1)) { //1 is raceType == 400m race
			System.out.println("You win!");	
			return true;
		}else {
			System.out.println("You lose!");
			return false;
		}
		
	}
	
	static boolean _800mRace(Car a, Car b) {
		if(isPlayerWinner(a, b, 2)) { //2 is raceType == 800m race
			System.out.println("You win!");	
			return true;
		}else {
			System.out.println("You lose!");
			return false;
		}
	}
	
	static boolean isPlayerWinner(Car a, Car b, int raceType) {
		double playerPoints = carPointCalculus(a, raceType);
		double enePoints = carPointCalculus(b, raceType);
		if(playerPoints > enePoints) {
			System.out.println("Your points: " + playerPoints + " opponent points: " + enePoints);
			return true;	
		}
		else {
			System.out.println("Your points: " + playerPoints + " opponent points: " + enePoints);
			return false;
		}
			
	}
	
	static double carPointCalculus(Car car, int raceType) {
		if(raceType == 1) 
			return ((10 - car.acceleration) * 50) + car.topSpeed;
		else
			return ((10 - car.acceleration) * 45) + (car.topSpeed * 1.2);
	}
}
