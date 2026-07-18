package notification;

import domain.Order;

public interface NotificationService {
    void notifyCustomers(Order order);
}
