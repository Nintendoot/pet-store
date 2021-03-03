package by.nintendo.petstore.storage;

import by.nintendo.petstore.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderStorage {
    private final List<Order> ordersList = new ArrayList<>();
    private  long orderId = 1;

    public void addOrder(Order order) {
        order.setId(orderId);
        ordersList.add(order);
        orderId++;
    }

    public void deleteOrder(Order order) {
        ordersList.remove(order);
    }

    public List<Order> getAllOrders() {
        return ordersList;
    }
}
