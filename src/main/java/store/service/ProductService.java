package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.ProductCollection;
import store.domain.checker.PromotionChecker;
import store.domain.constant.Resource;
import store.service.reader.MarkdownReader;

public class ProductService {
    private static final String DELIMITER = ",";
    private final MarkdownReader reader;
    private final PromotionChecker checker;

    public ProductService(MarkdownReader reader, PromotionChecker checker) {
        this.reader = reader;
        this.checker = checker;
    }

    public ProductCollection setProductCollection() {
        ProductCollection productCollection = new ProductCollection();

        for (String line : readLines()) {
            String[] tokens = line.split(DELIMITER);
            Product product = loadProduct(tokens);
            if (product.isActive()) {
                productCollection.addProduct(product);
            }
        }
        return productCollection;
    }

    private List<String> readLines() {
        return reader.readResource(Resource.PRODUCTS_FILE);
    }

    private Product loadProduct(String[] tokens) {
        String name = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int quantity = Integer.parseInt(tokens[2]);
        String promotionName = tokens[3].equals("null") ? null : tokens[3];

        return new Product(name, price, quantity, promotionName, isActiveProduct(promotionName));
    }

    private boolean isActiveProduct(String promotionName) {
        return promotionName == null || checker.isValidPromotion(promotionName);
    }
}
