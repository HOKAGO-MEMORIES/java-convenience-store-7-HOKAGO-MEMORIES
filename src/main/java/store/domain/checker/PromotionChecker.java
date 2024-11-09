package store.domain.checker;

import store.domain.PromotionCollection;

public class PromotionChecker {
    private final PromotionCollection promotionCollection;

    public PromotionChecker(PromotionCollection promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    public boolean isValidPromotion(String promotionName) {
        return promotionCollection.checkPromotion(promotionName);
    }
}
