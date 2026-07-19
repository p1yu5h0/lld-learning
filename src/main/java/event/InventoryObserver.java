package event;

import domain.Order;

public class InventoryObserver implements OrderObserver{
    @Override
    public void onOrderCompleted(Order order){
        System.out.println("--> [INVENTORY SYSTEM] Reserving stock for Order ID: " + order.id());
    }
}
