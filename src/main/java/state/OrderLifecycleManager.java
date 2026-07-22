package state;

import domain.Order;

public class OrderLifecycleManager {
    private final Order order;
    private OrderState currentState;

    public OrderLifecycleManager(Order order) {
        this.order = order;
        this.currentState = new CreatedState();
    }

    public void pay(){
        currentState.pay(this);
    }
    public void ship(){
        currentState.ship(this);
    }
    public void cancel(){
        currentState.cancel(this);
    }

    void setState(OrderState newState){
        this.currentState = newState;
    }

    public Order getOrder() {
        return order;
    }
 }
