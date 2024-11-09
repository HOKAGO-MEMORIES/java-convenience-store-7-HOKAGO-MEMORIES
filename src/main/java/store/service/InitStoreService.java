package store.service;

import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.domain.checker.PromotionChecker;
import store.service.reader.MarkdownReader;

public class InitStoreService {
    private final static MarkdownReader reader = new MarkdownReader();

    public InitStoreService() {
    }

    public static PromotionCollection initPromotionCollection() {
        PromotionService promotionService = new PromotionService(reader);
        return promotionService.setPromotionCollection();
    }

    public static ProductCollection initProductCollection(PromotionCollection promotionCollection) {
        PromotionChecker checker = new PromotionChecker(promotionCollection);
        ProductService productService = new ProductService(reader, checker);
        return productService.setProductCollection();
    }
}
