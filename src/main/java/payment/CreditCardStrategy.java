package payment;

public class CreditCardStrategy implements PaymentStrategy {
    private final String nameOnCard;
    private final String cardNumber;

    public CreditCardStrategy(String nameOnCard, String cardNumber) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        // Here you would normally call a bank API (like Stripe)
        System.out.println("--> [CREDIT CARD] Charging $" + amount + " to card ending in " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("--> [CREDIT CARD] Payment successful for " + nameOnCard);
        return true;
    }
}
