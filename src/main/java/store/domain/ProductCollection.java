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

    public boolean hasEnoughQuantity(String productName, int orderQuantity) {
        List<Product> matchingProducts = getProduct(productName);
        for (Product product : matchingProducts) {
            orderQuantity = product.decreaseQuantity(orderQuantity);
            if (orderQuantity <= 0) {
                return true;
            }
        }
        return false;
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
