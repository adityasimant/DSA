package LLD.NotificationSystem;

public class NotificationService {

    NotificationStrategy notificationStrategy;

    NotificationService(NotificationStrategy strategy){
        this.notificationStrategy = strategy;
    }

    public void setStrategy(NotificationStrategy strategy){
        this.notificationStrategy = strategy;
    }

    public void sendMessage(String message){
        this.notificationStrategy.sendNotification(message);
    }
}
