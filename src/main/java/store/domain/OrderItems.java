package store.domain;

public class OrderItems {
    private final String productName;
    private int orderQuantity;
    private int price;

    public OrderItems(String productName, int orderQuantity) {
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.price = 0;
    }

    public OrderItems(String productName, int orderQuantity, int price) {
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    public void increaseQuantity(int quantity) {
        this.orderQuantity += quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public int getPrice() {
        return price;
    }
}
