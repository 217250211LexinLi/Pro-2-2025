public class ERyder {
public static final String COMPANY_NAME = "ERyder";
public static final double BASE_FARE = 1.0;
public static final double PER_MINUTE_FARE = 0.5;
private final String LINKED_ACCOUNT;
private final String LINKED_PHONE_NUMBER ;
 int bikeID;
int batteryLevel;
int rideTime;
 boolean isAvailable;
double kmDriven;
private int totalUsageInMinutes;
private double totalFare;

 public ERyder() {
 this.LINKED_ACCOUNT = "guest";
 this.LINKED_PHONE_NUMBER = "00000000000";
 }

 public ERyder(int id, int bat, boolean ava, double km,  String linkedAccount, String linkedPhoneNumber) {
 bikeID = id;
setBatteryLevel(bat); 
 isAvailable = ava;
 kmDriven = km;
this.LINKED_ACCOUNT = linkedAccount;
 this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
} 
 public void ride() {
 if (batteryLevel > 0 && isAvailable) {
 System.out.println("Can ride.");
 } else {
 System.out.println("Can't ride.");
 }
 }
 public void printBikeDetails() {
 System.out.println("Bike ID: " + bikeID);
 System.out.println("Battery Level: " + batteryLevel + "%");
 System.out.println("Is Available: " + isAvailable);
 System.out.println("KM Driven: " + kmDriven + "km\n");
 }
public void printRideDetails(int rideTime) {
 this.rideTime=rideTime;
  System.out.println("Ride duration: " + rideTime + " minutes");
 System.out.println("Current bike ID: " + bikeID);
 System.out.println("User account: " + LINKED_ACCOUNT);
}
 public void setBatteryLevel(int bat) {
if (bat <0)
 batteryLevel=0;
 else if (bat >100)
 batteryLevel=100;
 else
 batteryLevel=bat;
 }
 public double calculateFare() {
 double fare = 0;
 if (rideTime <= 30) {
 fare = 2;
 } else {
fare = 2 + (rideTime - 30) * 0.1;
 }
 return fare;
}
}