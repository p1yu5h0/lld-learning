package facade;

import domain.Order;
import payment.PaymentStrategy;
import pricing.BaseOrderPrice;
import pricing.OrderPrice;
import pricing.ShippingDecorator;
import pricing.TaxDecorator;
import service.CheckoutService;

public class CheckoutFacade {
    private CheckoutService checkoutService;
    public CheckoutFacade(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }
    public void processCompleteOrder(Order order, String discountCode, PaymentStrategy paymentStrategy) {
        System.out.println("--- Facade: Processing ---");
        OrderPrice pricingEngine = new BaseOrderPrice(order);
        pricingEngine = new TaxDecorator(pricingEngine);
        pricingEngine = new ShippingDecorator(pricingEngine);

        double decoratedPrice = pricingEngine.getPrice();
        System.out.println("Final Calculated Price (Before Discount): $" + decoratedPrice);

        // 2. Delegate the updated order to the checkout process
        checkoutService.processCheckout(order, discountCode, paymentStrategy, decoratedPrice);
        System.out.println("--- Facade: Done! ---");
        System.out.println();
    }
}
