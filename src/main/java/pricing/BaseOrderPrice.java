package pricing;

import domain.Order;

//The Concrete Component (The Base)
public class BaseOrderPrice implements OrderPrice {
    private final Order order;

    public BaseOrderPrice(Order order) {
        this.order = order;
    }

    @Override
    public double getPrice() {
        return order.amount();
    }

    @Override
    public String getDescription() {
        return "Base Order (" + order.id() + ")";
    }
}
