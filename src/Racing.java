
public class Racing {

	
	static void _400mRace(Car a, Car b) {
		if(isPlayerWinner(a, b, 1)) { //1 is raceType == 400m race
			System.out.println("You win!");	
		}else {
			System.out.println("You lose!");
		}
		
	}
	
	static void _800mRace(Car a, Car b) {
		if(isPlayerWinner(a, b, 2)) { //2 is raceType == 800m race
			System.out.println("You win!");	
		}else {
			System.out.println("You lose!");
		}
	}
	
	static boolean isPlayerWinner(Car a, Car b, int raceType) {
		double playerPoints = carPointCalculus(a, raceType);
		double enePoints = carPointCalculus(b, raceType);
		if(playerPoints > enePoints)
			return true;
		return false;
			
	}
	
	static double carPointCalculus(Car car, int raceType) {
		if(raceType == 1) 
			return ((10 - car.acceleration) * 50) + car.topSpeed;
		else
			return ((10 - car.acceleration) * 45) + (car.topSpeed * 1.2);
	}
}
