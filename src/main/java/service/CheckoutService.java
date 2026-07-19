package service;

import cache.DiscountCache;
import domain.Order;
import notification.NotificationFactory;
import notification.NotificationService;
import payment.PaymentStrategy;
import repository.OrderRepository;

public class CheckoutService {
    private final OrderRepository repository;
    public CheckoutService(OrderRepository orderRepository){
        this.repository = orderRepository;
    }
    public void processCheckout(Order order, String notificiationChannel, String promoCode, PaymentStrategy paymentMethod){
        System.out.println("Starting checkout process for Order ID: " + order.id());
        System.out.println("Original Amount: $" + order.amount());

        // 1. Fetch the global Singleton cache
        DiscountCache cache = DiscountCache.getInstance();
        // 2. Apply discount logic
        double discountMultiplier = cache.getDiscountMultiplier(promoCode);
        double finalAmount = order.amount() - (order.amount() * discountMultiplier);
        if (discountMultiplier > 0) {
            System.out.println("Promo applied! " + promoCode + " gave " + (discountMultiplier * 100) + "% off. New Total: $" + finalAmount);
        } else if (promoCode != null && !promoCode.isBlank()) {
            System.out.println("Invalid promo code: " + promoCode + ". Proceeding with original amount.");
        }

        // --- THE STRATEGY PATTERN IN ACTION ---
        // We delegate the work to whatever behavior was passed in at runtime
        boolean isPaymentSuccessful = paymentMethod.pay(finalAmount);
        if(isPaymentSuccessful){
            repository.save(order);
            // 1. Ask the factory for the correct object based on runtime data
            NotificationService notifier = NotificationFactory.getNotifier(notificiationChannel);
            // 2. Execute the behavior without knowing exactly which class is running
            notifier.notifyCustomers(order);
            System.out.println("Checkout completed for order id: " + order.id());
        }

    }
}
