package state;

public class ShippedState implements OrderState{
    @Override
    public void pay(OrderLifecycleManager context) {
        throw new IllegalStateException("Order is already paid and shipped.");
    }

    @Override
    public void ship(OrderLifecycleManager context) {
        System.out.println("Order is already on its way.");
    }

    @Override
    public void cancel(OrderLifecycleManager context) {
        // In reality, this might transition to a "ReturnRequestedState",
        // but for this scope, we block it.
        throw new IllegalStateException("Cannot cancel an order that has already been shipped.");
    }
}
