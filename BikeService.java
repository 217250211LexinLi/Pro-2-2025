import java.util.*;
import java.time.LocalDateTime;

public class BikeService {
    private List<Bike> bikes;
    private Stack<ERyderLog> logs;
    private Queue<BikeRequest> requests;

    public BikeService() {
        bikes = new ArrayList<>();
        logs = new Stack<>();
        requests = new LinkedList<>();

        bikes.add(new Bike("B001", true, 100, null, "Station A"));
        bikes.add(new Bike("B002", false, 80, null, "Station B"));
        bikes.add(new Bike("B003", true, 90, null, "Station A"));
    }

    public void addLog(String id, String event) {
        logs.push(new ERyderLog(id, event, LocalDateTime.now()));
    }

    public void viewSystemLogs() {
        for (ERyderLog log : logs) {
            System.out.println(log);
        }
    }

    public void viewPending() {
        for (BikeRequest req : requests) {
            System.out.println(req);
        }
    }

    public boolean processNext() {
        if (!requests.isEmpty()) {
            System.out.println("Processing: " + requests.poll());
            return true;
        }
        return false;
    }

    public String bookBike(String location) {
        for (Bike b : bikes) {
            if (b.getLocation().equals(location) && b.isAvailable()) {
                b.setAvailable(false);
                addLog(b.getBikeId(), "Rented at " + location);
                return b.getBikeId();
            }
        }
        requests.add(new BikeRequest("user@test.com", location, LocalDateTime.now()));
        addLog("REQ", "Added to queue at " + location);
        return null;
    }
}