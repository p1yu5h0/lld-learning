import domain.Order;
import repository.InMemoryOrderDatabase;
import repository.OrderRepository;
import service.CheckoutService;
import payment.CreditCardStrategy;
import payment.PayPalStrategy;
import payment.PaymentStrategy;

public class Main {
    public static void main(String[] args) {
        OrderRepository database = new InMemoryOrderDatabase();
        CheckoutService checkoutService = new CheckoutService(database);

        Order firstOrder = new Order("ORD-1001", 250.20, "piyush@gmail.com");
        Order secondOrder = new Order("ORD-1002", 350.20, "agrawal@gmail.com");

        // We instantiate the specific algorithms we want to use
        PaymentStrategy aliceCard = new CreditCardStrategy("Alice Smith", "1234567890124444");
        PaymentStrategy bobPayPal = new PayPalStrategy("bob@startup.io");

        System.out.println("Processing first order ------[USING EMAIL BY FACTORY]------- [USING CREDIT CARD STRATEGY]");
        checkoutService.processCheckout(firstOrder, "EMAIL", "SAVE20", aliceCard);

        System.out.println();
        System.out.println();

        System.out.println("Processing second order ------[USING SMS BY FACTORY]------- [USING PAYPAL STRATEGY]");
        checkoutService.processCheckout(secondOrder, "SMS", "WINTER50", bobPayPal);

//        checkoutService.processCheckout(secondOrder, "WHATSAPP");
    }
}
