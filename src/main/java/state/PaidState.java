package state;

public class PaidState implements OrderState {

    @Override
    public void pay(OrderLifecycleManager context) {
        // Defensive check: Prevent double billing
        throw new IllegalStateException("Order is already paid, cannot process payment again");
    }

    @Override
    public void ship(OrderLifecycleManager context) {
        System.out.println("Shipping order: " + context.getOrder().id());
        // Valid transition: Move to Shipped
        context.setState(new ShippedState());
    }

    @Override
    public void cancel(OrderLifecycleManager context) {
        System.out.println("Cancelling order and initiating refund for amount: $" + context.getOrder().amount());
        // Valid transition: Move to Cancelled
        context.setState(new CancelledState());
    }
}
