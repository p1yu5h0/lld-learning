package notification;

//The new keyword is actually a source of tight coupling.
// If your main business logic (like a CheckoutService) is filled with if/else statements deciding whether
// to say new EmailNotification(), new SMSNotification(),
// or new PushNotification(), your core service is doing too much.
// It has to know exactly how to build every single type of notification.

//You extract all that object-creation logic and put it into a dedicated class (the Factory).
//The main system just asks the Factory for what it needs, and the Factory hands back a fully built object.
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
