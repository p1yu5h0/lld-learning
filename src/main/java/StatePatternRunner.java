import domain.Order;
import state.OrderLifecycleManager;

public class StatePatternRunner {
    public static void main(String[] args) {
        System.out.println("=== SCENARIO 1: The Happy Path ===");
        runHappyPath();

        System.out.println("\n=== SCENARIO 2: The Defensive Error Path ===");
        runErrorPath();
    }

    private static void runHappyPath() {
        // 1. Create the immutable data record
        Order order = new Order("ORD-1001", 250.00, "piyush200205@gmail.com");

        // 2. Initialize the state machine (Starts in CreatedState)
        OrderLifecycleManager lifecycle = new OrderLifecycleManager(order);
        System.out.println("Initial State: Order Created.");

        // 3. Move through the lifecycle
        lifecycle.pay();  // Output: Processing payment...
        lifecycle.ship(); // Output: Shipping order...

        System.out.println("Scenario 1 Complete. Order successfully fulfilled.");
    }

    private static void runErrorPath() {
        Order order = new Order("ORD-9999", 50.00, "piyush@gmail.com");
        OrderLifecycleManager lifecycle = new OrderLifecycleManager(order);

        try {
            System.out.println("Attempting to ship an unpaid order...");
            // This should immediately throw an IllegalStateException
            lifecycle.ship();
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        System.out.println("\nProcessing payment so we can test cancellation...");
        lifecycle.pay();

        System.out.println("Customer requested a cancellation...");
        lifecycle.cancel(); // Output: Cancelling order and initiating refund...

        try {
            System.out.println("Attempting to ship the cancelled order...");
            // This should also throw an IllegalStateException
            lifecycle.ship();
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }
    }
}
