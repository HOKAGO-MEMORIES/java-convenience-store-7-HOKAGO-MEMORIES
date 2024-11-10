package store.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCollection {
    private final List<Product> products;

    public ProductCollection() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean containsProduct(String productName) {
        return products.stream()
                .anyMatch(product -> product.hasName(productName));
    }

    public boolean hasEnoughQuantity(String productName, int quantity) {
        List<Product> matchingProducts = getProduct(productName);
        for (Product product : matchingProducts) {
            quantity = product.decreaseQuantity(quantity);
        }
        return quantity <= 0;
    }

    public List<Product> getProduct(String productName) {
        return products.stream()
                .filter(product -> product.hasName(productName))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder inventory = new StringBuilder();
        for (Product product : products) {
            inventory.append(product.toString()).append("\n");
        }
        return inventory.toString();
    }
}
