package LLD.NotificationSystem;

public class PushNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Via Push Notif: "+ message);
    }
}
