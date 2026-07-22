import domain.Order;
import event.DatabaseObserver;
import event.InventoryObserver;
import event.NotificationObserver;
import event.OrderEventManager;
import facade.CheckoutFacade;
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


        //Initializa the new Facade
        CheckoutFacade checkoutFacade = new CheckoutFacade(checkoutService);
        System.out.println("System boot complete.\n");

        // ---------------------------------------------------------
        // CLIENT EXECUTION (Using the Facade)
        // ---------------------------------------------------------
        System.out.println("=== PROCESSING ORDERS ===\n");

        Order order1 = new Order("ORD-1001", 100.00, "alice@example.com");
        PaymentStrategy aliceCard = new CreditCardStrategy("Alice Smith", "1234567890124444");

        Order order2 = new Order("ORD-1002", 200.00, "bob@example.com");
        PaymentStrategy bobPayPal = new PayPalStrategy("bob@startup.io");

        // The client simply asks the Facade to handle everything!
        System.out.println("--- Client: Submitting Order 1 ---");
        checkoutFacade.processCompleteOrder(order1, "SAVE20", aliceCard);

        System.out.println("--- Client: Submitting Order 2 ---");
        checkoutFacade.processCompleteOrder(order2, "WINTER50", bobPayPal);

        System.out.println("\n=== ENGINE SHUTDOWN ===");
    }
}