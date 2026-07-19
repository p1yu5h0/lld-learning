import domain.Order;
import repository.InMemoryOrderDatabase;
import repository.OrderRepository;
import service.CheckoutService;
import payment.CreditCardStrategy;
import payment.PayPalStrategy;
import payment.PaymentStrategy;

public class Main {
    public static void main(String[] args) {
        // 1. Create our core dependencies
        repository.OrderRepository database = new repository.InMemoryOrderDatabase();
        event.OrderEventManager eventManager = new event.OrderEventManager();

        // 2. Subscribe our listeners to the Event Manager
        eventManager.subscribe(new event.DatabaseObserver(database));
        eventManager.subscribe(new event.NotificationObserver("EMAIL"));
        eventManager.subscribe(new event.InventoryObserver());

        // 3. Inject the Event Manager into the Checkout Service
        service.CheckoutService checkoutService = new service.CheckoutService(eventManager);

        // 4. Run the code
        domain.Order order1 = new domain.Order("ORD-1001", 100.00, "customer1@example.com");
        payment.PaymentStrategy aliceCard = new payment.CreditCardStrategy("Alice Smith", "1234567890124444");

        System.out.println("--- Processing First Order ---");
        checkoutService.processCheckout(order1, "SAVE20", aliceCard);
    }
}
