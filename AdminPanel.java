import java.util.Scanner;

public class AdminPanel {
    private BikeService service;
    private Scanner sc;

    public AdminPanel(BikeService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("\n1. View Logs\n2. View Requests\n3. Process Next\n4. Exit");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) {
                service.viewSystemLogs();
            } else if (opt == 2) {
                service.viewPending();
            } else if (opt == 3) {
                System.out.println(service.processNext() ? "Done" : "Empty");
            } else if (opt == 4) {
                break;
            }
        }
    }
}