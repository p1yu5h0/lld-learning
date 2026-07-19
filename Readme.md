# E-Commerce Checkout Engine: Architecture Study Guide

## Overview
This project is a modular, event-driven E-commerce Checkout Engine built using Java 21. It demonstrates the transition from a tightly coupled procedural script into a highly scalable, decoupled backend system.

The codebase heavily emphasizes SOLID principles—specifically the Open-Closed Principle and Dependency Inversion—favoring composition over inheritance to meet the standards of highly scalable backend architectures.

---

## Design Patterns Reference Guide

Use the table below to navigate the codebase and review the implementation details of each specific architectural pattern.

| Pattern | Category | Architectural Purpose | Classes to Review |
| :--- | :--- | :--- | :--- |
| **Singleton** | Creational | Prevents redundant database calls by keeping exactly one shared instance of data in memory across the application. | `cache.DiscountCache` |
| **Factory** | Creational | Abstracts object creation. Hides the `new` keyword and instantiation logic for notifications away from the core business service. | `notification.NotificationFactory` |
| **Strategy** | Behavioral | Enables interchangeable behaviors. Allows the system to swap different payment algorithms at runtime without modifying business logic or using rigid `if/else` blocks. | `payment.PaymentStrategy`, `payment.CreditCardStrategy`, `payment.PayPalStrategy` |
| **Observer** | Behavioral | Facilitates Event-Driven Architecture. Decouples side-effects (inventory reservation, database saving, emails) from the main checkout flow via a centralized event bus. | `event.OrderEventManager`, `event.OrderObserver`, `event.InventoryObserver` |
| **Decorator** | Structural | Prevents class explosion. Dynamically stacks pricing rules (taxes, shipping, gift wrapping) at runtime using composition instead of rigid class inheritance. | `pricing.OrderPriceDecorator`, `pricing.TaxDecorator`, `pricing.BaseOrderPrice` |

---

## Code Execution Flow

To see the patterns in action, run the `Main.java` file. The execution is broken into three distinct phases to demonstrate pattern integration:

1. **System Boot (Observer Setup):**
   The Event Bus is initialized and various independent systems (Database, Notification, Inventory) are subscribed as listeners.
2. **Order Processing (Strategy & Singleton):**
   Orders are processed using dynamically injected payment methods. The `DiscountCache` is loaded into memory only once during the first transaction.
3. **Dynamic Pricing (Decorator):**
   A base order is instantiated and seamlessly wrapped in multiple fee layers to calculate a highly complex final total without altering the base order object.

## Core Philosophies Implemented
*   **Dependency Injection:** The `CheckoutService` never instantiates its own dependencies. They are passed in via the constructor or method signatures.
*   **Interface-Driven Design:** Components communicate through contracts (`OrderPrice`, `OrderObserver`, `PaymentStrategy`) rather than concrete implementations.
*   **Open-Closed Principle:** The system is open for extension (e.g., adding a new `HolidaySurchargeDecorator` or `CryptoPaymentStrategy`) but closed for modification (the core `CheckoutService` remains untouched).