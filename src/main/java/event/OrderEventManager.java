package event;

import domain.Order;

import java.util.ArrayList;
import java.util.List;

//The Publisher/Subscriber (Pub-Sub) model.
// The core system does its job and then simply shouts into the void, "Hey, an order just finished!"
// It doesn't know who is listening, and it doesn't care.
// The other systems (the Observers) listen for that shout and trigger their own logic independently.
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
