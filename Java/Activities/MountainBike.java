package activities;

public class MountainBike extends Bicycle {
 int seatHeight;
	public MountainBike(int gears, int currentSpeed,int seatHeight) {
		super(gears, currentSpeed);
		this.seatHeight = seatHeight;
	}

public String bicycleDesc() {
	System.out.println("seatHeight:"+seatHeight);
	return seatHeight+"";
}

public static void main(String args[]) {
    MountainBike mb = new MountainBike(3, 0, 25);
    System.out.println(mb.bicycleDesc());
    mb.speedUp(20);
    mb.applyBrake(5);
    Bicycle b = new Bicycle(3, 25);
    System.out.println(b.bicycleDesc());
}
}
