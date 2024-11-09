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
}
