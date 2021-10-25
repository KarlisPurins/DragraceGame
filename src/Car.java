
public class Car {
//topSpeed, acceleration, name, cost, 

	int topSpeed, cost; 
	double acceleration;
	String name;
	
	public Car(String gname, double gacceleration, int gtopSpeed, int gcost) {
		this.name = gname;
		this.acceleration = gacceleration;
		this.topSpeed = gtopSpeed;
		this.cost = gcost;
	}
	
	void costReductionAfterBuying() {
		this.cost /= 2;
	}
	
	public String toString() {
		return "	" + this.name + "	" + this.acceleration + "	" + this.topSpeed + "	" + this.cost + " $";
	}
}