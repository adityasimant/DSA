package LLD.NotificationSystem;



public class SMSNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Via SMS: "+message);
    }
}