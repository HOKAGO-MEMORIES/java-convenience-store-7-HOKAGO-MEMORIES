package store.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.util.Clock;

public class PromotionCollection {
    private final List<Promotion> promotions;

    public PromotionCollection() {
        this.promotions = new ArrayList<>();
    }

    public void addPromotion(final Promotion promotion) {
        if (promotion.isActive(getCurrentDate())) {
            promotions.add(promotion);
        }
    }

    private LocalDate getCurrentDate() {
        return Clock.now();
    }

    public boolean checkPromotion(String promotionName) {
        return promotions.stream()
                .anyMatch(promotion -> promotion.hasName(promotionName));
    }

    public boolean checkTwoPlusOne(String promotionName) {
        return getPromotionBuy(promotionName) == 2 && getPromotionGet(promotionName) == 1;
    }

    public boolean checkOnePlusOne(String promotionName) {
        return getPromotionBuy(promotionName) == 1 && getPromotionGet(promotionName) == 1;
    }

    private int getPromotionBuy(String promotionName) {
        return getPromotions(promotionName).getBuy();
    }

    private int getPromotionGet(String promotionName) {
        return getPromotions(promotionName).getGet();
    }

    private Promotion getPromotions(String promotionName) {
        return promotions.stream()
                .filter(product -> product.hasName(promotionName))
                .findFirst()
                .orElse(null);
    }
}
