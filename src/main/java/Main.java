import domain.Order;
import event.DatabaseObserver;
import event.InventoryObserver;
import event.NotificationObserver;
import event.OrderEventManager;
import payment.CreditCardStrategy;
import payment.PayPalStrategy;
import payment.PaymentStrategy;
import pricing.BaseOrderPrice;
import pricing.GiftWrapDecorator;
import pricing.OrderPrice;
import pricing.ShippingDecorator;
import pricing.TaxDecorator;
import repository.InMemoryOrderDatabase; // Assuming this is your Phase 1 repository implementation
import repository.OrderRepository;
import service.CheckoutService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE CHECKOUT ENGINE INITIALIZED ===\n");

        // ---------------------------------------------------------
        // PHASE 1-3 & 5: SETUP AND OBSERVER (EVENT-DRIVEN) PATTERN
        // ---------------------------------------------------------
        System.out.println("--- Booting up Event Systems ---");

        // 1. Create core database dependency
        OrderRepository database = new InMemoryOrderDatabase();

        // 2. Create the Publisher (Event Manager)
        OrderEventManager eventManager = new OrderEventManager();

        // 3. Register Subscribers (Observers)
        eventManager.subscribe(new DatabaseObserver(database));
        eventManager.subscribe(new NotificationObserver("EMAIL")); // Uses NotificationFactory inside
        eventManager.subscribe(new InventoryObserver());

        // 4. Inject Event Manager into the Checkout Service
        CheckoutService checkoutService = new CheckoutService(eventManager);

        System.out.println("System boot complete.\n");


        // ---------------------------------------------------------
        // PHASE 4: STRATEGY PATTERN IN ACTION
        // ---------------------------------------------------------
        System.out.println("=== PROCESSING ORDERS (STRATEGY & OBSERVER) ===\n");

        Order order1 = new Order("ORD-1001", 100.00, "alice@example.com");
        Order order2 = new Order("ORD-1002", 200.00, "bob@example.com");

        // Instantiate specific algorithms for payment
        PaymentStrategy aliceCard = new CreditCardStrategy("Alice Smith", "1234567890124444");
        PaymentStrategy bobPayPal = new PayPalStrategy("bob@startup.io");

        System.out.println("--- Order 1 (Credit Card) ---");
        // Uses Singleton DiscountCache inside
        checkoutService.processCheckout(order1, "SAVE20", aliceCard);

        System.out.println("--- Order 2 (PayPal) ---");
        checkoutService.processCheckout(order2, "WINTER50", bobPayPal);


        // ---------------------------------------------------------
        // PHASE 6: DECORATOR PATTERN IN ACTION
        // ---------------------------------------------------------
        System.out.println("=== DYNAMIC PRICING ENGINE (DECORATOR) ===\n");

        // 1. Create a raw order (Base Price: $100.00)
        Order order3 = new Order("ORD-1003", 100.00, "customer3@example.com");

        // 2. Start with the Base Component
        OrderPrice finalPrice = new BaseOrderPrice(order3);

        // 3. Wrap it in Tax (10%)
        finalPrice = new TaxDecorator(finalPrice);

        // 4. Wrap it in Shipping ($15 flat)
        finalPrice = new ShippingDecorator(finalPrice);

        // 5. Wrap it in Gift Wrapping ($5 flat)
        finalPrice = new GiftWrapDecorator(finalPrice);

        // Calculate and Print
        System.out.println("Receipt: " + finalPrice.getDescription());
        System.out.println("Total Amount Due: $" + finalPrice.getPrice());
        System.out.println("\n=== ENGINE SHUTDOWN ===");
    }
}