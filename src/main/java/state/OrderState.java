package state;

public interface OrderState {
    void pay(OrderLifecycleManager context);
    void ship(OrderLifecycleManager context);
    void cancel(OrderLifecycleManager context);
}
