package store.domain;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;
    private final boolean isActive;

    public Product(final String name, final int price, final int quantity, final String promotionName,
                   final boolean isActive) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
        this.isActive = isActive;
    }

    public void changeQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean hasName(String productName) {
        return name.equals(productName);
    }

    public boolean hasPromotion() {
        return !promotionName.equals("null");
    }

    public String getPromotionName() {
        return promotionName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int decreaseQuantity(int quantity) {
        return quantity - this.quantity;
    }

    @Override
    public String toString() {
        String checkedQuantity = (quantity == 0) ? "재고 없음" : String.format("%d개", quantity);
        String checkedPromotionName = (promotionName.equals("null")) ? "" : promotionName;
        return String.format("- %s %,d원 %s %s", name, price, checkedQuantity, checkedPromotionName);
    }
}
