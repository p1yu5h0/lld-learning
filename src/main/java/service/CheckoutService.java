package service;

import domain.Order;
import notification.NotificationFactory;
import notification.NotificationService;
import repository.OrderRepository;

public class CheckoutService {
    private final OrderRepository repository;
    public CheckoutService(OrderRepository orderRepository){
        this.repository = orderRepository;
    }
    public void processCheckout(Order order, String notificiationChannel){
        System.out.println("Starting with Order Id: " + order.id());
        repository.save(order);

        // 1. Ask the factory for the correct object based on runtime data
        NotificationService notifier = NotificationFactory.getNotifier(notificiationChannel);

        // 2. Execute the behavior without knowing exactly which class is running
        notifier.notifyCustomers(order);

        System.out.println("Checkout completed for order id: " + order.id());
    }
}
