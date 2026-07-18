package notification;

import domain.Order;

public class SMSNotification implements NotificationService {
    @Override
    public void notifyCustomers(Order order) {
        System.out.println("[SMS] sending sms to customer for order id: " + order.id());
    }
}
