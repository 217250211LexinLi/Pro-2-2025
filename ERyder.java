public class ERyder {
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String linkedAccount;
    private final String linkedPhone;

    int bikeID;
    int batteryLevel;
    int rideTime;
    boolean isAvailable;
    double kmDriven;
    private int totalUsageInMinutes;
    private double totalFare;

    public ERyder() {
        this.linkedAccount = "guest";
        this.linkedPhone = "0000000000";
    }

    public ERyder(int id, int bat, boolean ava, double km, String linkedAccount, String linkedPhone) {
        this.bikeID = id;
        this.batteryLevel = bat;
        this.isAvailable = ava;
        this.kmDriven = km;
        this.linkedAccount = linkedAccount;
        this.linkedPhone = linkedPhone;
    }

    public void setBikeID(int id) {
        this.bikeID = id;
    }

    public void setBatteryLevel(int bat) {
        this.batteryLevel = bat;
    }

    public void addRide(int minutes) {
        this.totalUsageInMinutes += minutes;
        this.totalFare = BASE_FARE + (minutes * PER_MINUTE_FARE);
    }

    public double getTotalFare() {
        return this.totalFare;
    }
   
    public String getLinkedAccount() {
    return linkedAccount;
}

public String getLinkedPhone() {
    return linkedPhone;
}

public int totalUsageInMinutes() {
    return totalUsageInMinutes;
}


}


