package event;

import domain.Order;
import repository.OrderRepository;

public class DatabaseObserver implements OrderObserver {
    private final OrderRepository orderRepository;

    public DatabaseObserver(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void onOrderCompleted(Order order) {
        orderRepository.save(order);
    }
}
