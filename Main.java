public class Main {
    public static void main(String[] args) {
        BikeService s = new BikeService();
        AdminPanel a = new AdminPanel(s);
        a.show();
    }
}