public class Main {
    public static void main(String[] args) {
ERyder bikeDefault = new ERyder();
System.out.println("Bike created with Default Constructor");
bikeDefault.printBikeDetails();
ERyder bikeFull = new ERyder(1001, 75, true, 123.5);
System.out.println("Bike created with Parameterized Constructor");
bikeFull.ride();
bikeFull.printBikeDetails();
    }
}