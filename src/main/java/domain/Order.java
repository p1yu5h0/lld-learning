package domain;


public record Order(String id, double amount, String customerEmail) {
    // Java records automatically generate constructors, getters (e.g., order.id()),
    // equals(), hashCode(), and toString() for us.
}
