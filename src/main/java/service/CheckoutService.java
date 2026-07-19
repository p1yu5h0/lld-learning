package service;

import cache.DiscountCache;
import domain.Order;
import event.OrderEventManager;
import payment.PaymentStrategy;

public class CheckoutService {

    private final OrderEventManager eventManager;

    public CheckoutService(OrderEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void processCheckout(Order order, String promoCode, PaymentStrategy paymentMethod) {
        System.out.println("Starting checkout process for Order ID: " + order.id());

        DiscountCache cache = DiscountCache.getInstance();
        double discountMultiplier = cache.getDiscountMultiplier(promoCode);
        double finalAmount = order.amount() - (order.amount() * discountMultiplier);

        boolean isPaymentSuccessful = paymentMethod.pay(finalAmount);

        if (isPaymentSuccessful) {
            // We just trigger the event. We don't care who is listening!
            eventManager.notifyOrderCompleted(order);
            System.out.println("Checkout completed for Order ID: " + order.id() + "\n");
        } else {
            System.out.println("Checkout failed due to payment error for Order ID: " + order.id() + "\n");
        }
    }
}