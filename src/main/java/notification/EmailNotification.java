package notification;

import domain.Order;

public class EmailNotification implements NotificationService {
    @Override
    public void notifyCustomers(Order order) {
        System.out.println("[EMAIL] sending receipt to: " + order.customerEmail());
    }
}
