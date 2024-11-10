package store.extractor;

import store.domain.OrderItems;
import store.domain.OrderItemsCollection;

public class OrderExtractor {
    private static final String DELIMITER = ",";
    private static final String HYPHEN = "-";

    public static OrderItemsCollection extractOrder(String order) {
        String[] orders = order.split(DELIMITER);
        return makeOrderItemsCollection(orders);
    }

    private static OrderItemsCollection makeOrderItemsCollection(String[] orders) {
        OrderItemsCollection orderItemsCollection = new OrderItemsCollection();

        for (String productOrder : orders) {
            String[] OrderPart = extractProductOrder(productOrder);
            orderItemsCollection.addOrderItems(new OrderItems(OrderPart[0], Integer.parseInt(OrderPart[1])));
        }
        return orderItemsCollection;
    }

    private static String[] extractProductOrder(String productOrder) {
        productOrder = productOrder.replace("[", "").replace("]", "");
        return productOrder.split(HYPHEN);
    }
}
