package notification;

public class NotificationFactory {
    public static NotificationService getNotifier(String channel){
        // Java 21 switch expressions return a value directly and require no break statements
        return switch (channel.toUpperCase()){
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SMSNotification();
            default -> throw new IllegalArgumentException("Invalid channel");
        };
    }
}
