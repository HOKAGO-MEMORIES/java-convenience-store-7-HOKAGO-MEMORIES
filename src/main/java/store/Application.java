package store;

import store.config.AppInitializer;
import store.controller.StoreController;
import store.domain.ProductCollection;
import store.domain.PromotionCollection;

public class Application {
    public static void main(String[] args) {
        PromotionCollection promotionCollection = AppInitializer.initPromotionCollection();
        ProductCollection productCollection = AppInitializer.initProductCollection(promotionCollection);

        StoreController storeController = new StoreController(promotionCollection, productCollection);
        storeController.run();
    }
}
