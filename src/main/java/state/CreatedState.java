package state;

public class CreatedState implements OrderState{

    @Override
    public void pay(OrderLifecycleManager context) {
        System.out.println("Processing payment for order: " + context.getOrder().id());
        context.setState(new PaidState());
    }

    @Override
    public void ship(OrderLifecycleManager context) {
        throw new IllegalStateException("Cannot ship an Unpaid Order");
    }

    @Override
    public void cancel(OrderLifecycleManager context) {
        System.out.println("Order cancelled");
        context.setState(new CancelledState());
    }
}
