package LLD.NotificationSystem;

public class NotificationMain {
    public static void main(String[] args) {
        NotificationService service = new NotificationService(NotificationStrategyFactory.getStrategy("sms"));

        service.sendMessage("Welcome to the platform!");

        service.setStrategy(new SMSNotification());
        service.sendMessage("Your OTP is 456789");

        service.setStrategy(new PushNotification());
        service.sendMessage("You have a new notification.");
    }
}
