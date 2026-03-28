import java.time.LocalDateTime;
import java.util.ArrayList;
public class BikeDatabase {
    public static ArrayList<Bike> bikes = new ArrayList<>();

    static {
        bikes.add(new Bike("B001", true, 95, LocalDateTime.now().minusDays(1), "Nanjing South"));
        bikes.add(new Bike("B002", true, 88, LocalDateTime.now().minusDays(2), "Nanjing South"));
        bikes.add(new Bike("B003", false, 72, LocalDateTime.now().minusHours(3), "Xinjiekou"));
        bikes.add(new Bike("B004", true, 90, LocalDateTime.now().minusDays(1), "Xinjiekou"));
        bikes.add(new Bike("B005", true, 85, LocalDateTime.now().minusDays(3), "Confucius Temple"));
    }
}


