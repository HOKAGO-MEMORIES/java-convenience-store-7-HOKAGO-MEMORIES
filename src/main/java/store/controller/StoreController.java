package store.controller;

import store.domain.ProductCollection;
import store.domain.PromotionCollection;

public class StoreController {

    private final PromotionCollection promotionCollection;
    private final ProductCollection productCollection;

    public StoreController(PromotionCollection promotionCollection, ProductCollection productCollection) {
        this.promotionCollection = promotionCollection;
        this.productCollection = productCollection;
    }

    public void run() {
        while (true) {
            new PurchaseController(productCollection);
        }

    }
}
