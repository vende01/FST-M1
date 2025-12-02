package activities;

public class Activity1 {

    public static void main(String[] args) {
        Car toyota = new Car(2014,"Black","Manual");

    
        //Using Car class method
        toyota.displayCharacterstics();
        toyota.accelerate();
        toyota.brake();
    }

}