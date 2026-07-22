package payment;

//To use the Strategy pattern, you must first instantiate a concrete implementation of that strategy (the object),
//and then pass that object to the system that will eventually use its methods.
public interface PaymentStrategy {
    boolean pay(double amount);
}
