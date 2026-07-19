package pricing;

//We need a shared contract that both the base order and all the "wrappers" will implement.
public interface OrderPrice {
    double getPrice();
    String getDescription();
}
