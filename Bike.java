    import java.time.LocalDateTime;

public class Bike {
    private String bikeId;
    private boolean available;
    private int battery;
    private LocalDateTime lastUsed;
    private String location;

    public Bike(String bikeId, boolean available, int battery, LocalDateTime lastUsed, String location) {
        this.bikeId = bikeId;
        this.available = available;
        this.battery = battery;
        this.lastUsed = lastUsed;
        this.location = location;
    }

    public String getBikeId() {
        return bikeId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getLocation() {
        return location;
    }

    public int getBattery() {
    return battery;
}

public LocalDateTime getLastUsed() {
    return lastUsed;
}
}