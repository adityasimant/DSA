package LLD.NotificationSystem;

public class EmailNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Via email: "+message);
    }
}
