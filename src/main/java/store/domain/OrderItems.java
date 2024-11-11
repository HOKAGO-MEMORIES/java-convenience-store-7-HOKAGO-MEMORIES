package store.domain;

public class OrderItems {
    private final String productName;
    private final int orderQuantity;
    private int price;

    public OrderItems(String productName, int orderQuantity) {
        this.productName = productName;
        this.orderQuantity = orderQuantity;
    }

    public OrderItems(String productName, int orderQuantity, int price) {
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public int getTotalPrice() {
        return price * orderQuantity;
    }
}
