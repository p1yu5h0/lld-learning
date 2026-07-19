package payment;

public class PayPalStrategy implements PaymentStrategy {
    private final String emailId;
    public PayPalStrategy(String emailId){
        this.emailId = emailId;
    }
    @Override
    public boolean pay(double amount) {
        // Here you would normally route through PayPal's OAuth and API
        System.out.println("--> [PAYPAL] Connecting to PayPal for account: " + emailId);
        System.out.println("--> [PAYPAL] Payment of $" + amount + " authorized successfully.");
        return true;
    }
}
