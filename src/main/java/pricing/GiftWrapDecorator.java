package pricing;

public class GiftWrapDecorator extends OrderPriceDecorator {

    public GiftWrapDecorator(OrderPrice wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 5.00;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Gift Wrap ($5)";
    }
}