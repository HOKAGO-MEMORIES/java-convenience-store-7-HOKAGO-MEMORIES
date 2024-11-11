package store.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import store.domain.Product;
import store.domain.ProductCollection;
import store.domain.checker.PromotionChecker;
import store.domain.constant.Resource;
import store.service.reader.MarkdownReader;

public class ProductService {
    private static final String DELIMITER = ",";
    private static final String NO_PROMOTION = "null";
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

    public void updateProductQuantity(String productName, int quantityToSubtract) {
        List<String> lines = readLines();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tokens = line.split(DELIMITER);
            String name = tokens[0];
            int currentQuantity = Integer.parseInt(tokens[2]);

            if (name.equals(productName)) {
                int newQuantity = currentQuantity - quantityToSubtract;
                tokens[2] = String.valueOf(newQuantity);
                lines.set(i, String.join(DELIMITER, tokens));
                break;
            }
        }
        writeUpdatedLinesToFile(lines);
    }

    private List<String> readLines() {
        return reader.readResource(Resource.PRODUCTS_FILE);
    }

    private void writeUpdatedLinesToFile(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.PRODUCTS_FILE.getFileName()))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product loadProduct(String[] tokens) {
        String name = tokens[0];
        int price = Integer.parseInt(tokens[1]);
        int quantity = Integer.parseInt(tokens[2]);
        String promotionName = tokens[3];

        return new Product(name, price, quantity, promotionName, isActiveProduct(promotionName));
    }

    private boolean isActiveProduct(String promotionName) {
        return promotionName.equals(NO_PROMOTION) || checker.isValidPromotion(promotionName);
    }
}
