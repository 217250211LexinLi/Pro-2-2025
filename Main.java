public class Main {
public static void main(String[] args) {
 ERyder bike1 = new ERyder();
 bike1.printBikeDetails();
 ERyder bike2 = new ERyder();
 bike2.bikeID=1;
 bike2.batteryLevel=70;
 bike2.isAvailable=true;
 bike2.kmDriven=100.0;
 bike2.ride();
 bike2.printBikeDetails();
bike2.printRideDetails(15);
  double fare = bike2.calculateFare();
 System.out.println("Ride fare: $" + fare);
 }
}