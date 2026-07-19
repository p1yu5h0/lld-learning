package event;

import domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderEventManager {
    private final List<OrderObserver> listners = new ArrayList<>();

    // Add a subscriber
    public void subscribe(OrderObserver listener){
        listners.add(listener);
    }

    public void notifyOrderCompleted(Order order){
        System.out.println("[EVENT BUS] Broadcasting OrderCompleted event for: " + order.id());
        for(OrderObserver listener : listners){
            listener.onOrderCompleted(order);
        }
    }
}
