package activities;
class Car{
	int make;
	String color;
	String transmission;
	public Car(int make, String color, String transmission) {
		super();
		this.make = make;
		this.color = color;
		this.transmission = transmission;
	}
	public void displayCharacterstics() {
		System.out.println("Color of the Car: " + color);
		System.out.println("Make of the Car: " + make);
		System.out.println("Transmission of the Car: " + transmission);
		
	}
	public void accelerate() {
		System.out.println("Car is moving forward.");
		
	}
	public void brake() {
		System.out.println("Car has stopped.");
		
	}
	
	
}