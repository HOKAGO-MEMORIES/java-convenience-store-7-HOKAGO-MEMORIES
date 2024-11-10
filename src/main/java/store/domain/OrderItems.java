package store.domain;

public class OrderItems {
    private final String productName;
    private final int orderQuantity;

    public OrderItems(String productName, int orderQuantity) {
        this.productName = productName;
        this.orderQuantity = orderQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
}
