package pricing;

public class TaxDecorator extends OrderPriceDecorator {
    public TaxDecorator(OrderPrice wrappedOrder) {
        super(wrappedOrder);
    }
    @Override
    public double getPrice(){
        double currentPrice = super.getPrice();
        return currentPrice + (currentPrice * 0.10);
    }
    @Override
    public String getDescription(){
        return super.getDescription() + " + State Tax (10%)";
    }
}
