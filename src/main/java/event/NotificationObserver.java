package event;

import domain.Order;
import notification.NotificationFactory;
import notification.NotificationService;

public class NotificationObserver implements OrderObserver {

    private final String channel;

    public NotificationObserver(String channel) {
        this.channel = channel;
    }
    @Override
    public void onOrderCompleted(Order order) {
        NotificationService notifier = NotificationFactory.getNotifier(channel);
        notifier.notifyCustomers(order);
    }
}
