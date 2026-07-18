package repository;

import domain.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderDatabase implements OrderRepository {

    private final Map<String, Order> database = new HashMap<>();
    @Override
    public void save(Order order) {
        System.out.println("Connecting to inmemory database...");
        database.put(order.id(), order);
        System.out.println("Order: " + order.id() + " has been saved!");
        System.out.println("Total orders: " + database.size());
    }
}
