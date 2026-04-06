import java.time.LocalDateTime;

public class BikeRequest {
    private String email;
    private String location;
    private LocalDateTime time;

    public BikeRequest(String email, String location, LocalDateTime time) {
        this.email = email;
        this.location = location;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email + " at " + location + " (" + time + ")";
    }
}