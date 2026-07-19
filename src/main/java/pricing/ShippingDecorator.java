package pricing;

public class ShippingDecorator extends OrderPriceDecorator {

    public ShippingDecorator(OrderPrice wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 15.00;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Flat Shipping ($15)";
    }
}