public class Car {
//topSpeed, acceleration, name, cost, 

	int topSpeed, cost; 
	double acceleration;
	String name;
	
	public Car(int gtopSpeed, double gacceleration, String gname, int gcost) {
		this.topSpeed = gtopSpeed;
		this.acceleration = gacceleration;
		this.name = gname;
		this.cost = gcost;
	}
	
	void costReductionAfterBuying() {
		this.cost /= 2;
	}
	
	public String toString() {
		return this.topSpeed + " " + this.acceleration + " " + this.name + " " + this.cost;
	}
}