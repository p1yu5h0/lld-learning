package event;

import domain.Order;

public interface OrderObserver {
    void onOrderCompleted(Order order);
}
