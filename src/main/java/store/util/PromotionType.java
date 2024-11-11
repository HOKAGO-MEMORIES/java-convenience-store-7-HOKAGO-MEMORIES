package store.util;

public enum PromotionType {
    TWO_PLUS_ONE(3, 2, 1),
    ONE_PLUS_ONE(2, 1, 1);

    private final int promotionGroupSize;
    private final int buyQuantity;
    private final int freeQuantity;

    PromotionType(int promotionGroupSize, int buyQuantity, int freeQuantity) {
        this.promotionGroupSize = promotionGroupSize;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }

    public int getPromotionGroupSize() {
        return promotionGroupSize;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }
}
