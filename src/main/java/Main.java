import domain.Order;
import repository.InMemoryOrderDatabase;
import repository.OrderRepository;
import service.CheckoutService;

public class Main {
    public static void main(String[] args) {
        OrderRepository database = new InMemoryOrderDatabase();
        CheckoutService checkoutService = new CheckoutService(database);

        Order firstOrder = new Order("ORD-1001", 250.20, "piyush@gmail.com");
        Order secondOrder = new Order("ORD-1002", 350.20, "agrawal@gmail.com");

        System.out.println("Processing first order -------------");
        checkoutService.processCheckout(firstOrder, "EMAIL");

        System.out.println("Processing second order -------------");
        checkoutService.processCheckout(secondOrder, "SMS");

//        checkoutService.processCheckout(secondOrder, "WHATSAPP");
    }
}
