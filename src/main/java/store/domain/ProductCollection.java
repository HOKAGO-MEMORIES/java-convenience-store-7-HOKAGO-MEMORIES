package store.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductCollection {
    private final List<Product> products;

    public ProductCollection() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
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
