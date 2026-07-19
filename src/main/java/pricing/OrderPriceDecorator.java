package pricing;

//Base Decorator
//Abstract Decorator

//The Base Decorator implements the interface, but it also holds a reference to the interface.
//This allows it to act as a wrapper.
public class OrderPriceDecorator implements OrderPrice {
    protected final OrderPrice wrappedOrder;

    public OrderPriceDecorator(OrderPrice wrappedOrder) {
        this.wrappedOrder = wrappedOrder;
    }

    @Override
    public double getPrice() {
        return wrappedOrder.getPrice();
    }

    @Override
    public String getDescription() {
        return wrappedOrder.getDescription();
    }
}
