package state;

public class CancelledState implements OrderState {

    @Override
    public void pay(OrderLifecycleManager context) {
        throw new IllegalStateException("Cannot process payment for a cancelled order.");
    }

    @Override
    public void ship(OrderLifecycleManager context) {
        throw new IllegalStateException("Cannot ship a cancelled order.");
    }

    @Override
    public void cancel(OrderLifecycleManager context) {
        // It's already cancelled, so we can either throw an exception
        // or just quietly ignore the redundant request.
        System.out.println("Order is already cancelled. No action taken.");
    }
}