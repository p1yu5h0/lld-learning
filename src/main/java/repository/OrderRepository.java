package repository;

import domain.Order;

//dependency inversion principle
//if we want to in future make a new order database, then we don't need to make changes in checkout service
//along with inmemoryorderdatabase we can have other database implementation
//and override save method of orderRepository

public interface OrderRepository {
    void save(Order order);
}
