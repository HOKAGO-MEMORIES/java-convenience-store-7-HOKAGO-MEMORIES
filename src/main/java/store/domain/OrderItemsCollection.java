package store.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsCollection {
    private final List<OrderItems> orderItemsCollection;

    public OrderItemsCollection() {
        orderItemsCollection = new ArrayList<>();
    }

    public void addOrderItems(OrderItems orderItems) {
        orderItemsCollection.add(orderItems);
    }

    public List<OrderItems> getOrderItems() {
        return new ArrayList<>(orderItemsCollection);
    }
}
