package LLD.NotificationSystem;

public class NotificationStrategyFactory {

    public static NotificationStrategy getStrategy(String type){
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null!");
        }

        switch (type.toLowerCase()) {
            case "sms":
                return new SMSNotification();
            case "email":
                return new EmailNotification();
            case "push":
                return new PushNotification();
            default:
                System.out.println("Unknown type. Defaulting to SMS!");
                return new SMSNotification();
        }
    }
}
