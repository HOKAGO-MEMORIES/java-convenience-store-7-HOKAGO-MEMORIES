package store.domain;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;

    public Product(final String name, final int price, final int quantity, final String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public void changeQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String checkedQuantity = (quantity == 0) ? "재고 없음" : String.format("%d개", quantity);
        String checkedPromotionName = (promotionName == null) ? "" : promotionName;
        return String.format("- %s %d원 %s %s", name, price, checkedQuantity, checkedPromotionName);
    }
}
