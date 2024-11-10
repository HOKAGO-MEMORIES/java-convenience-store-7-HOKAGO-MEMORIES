package store.request;

import store.domain.ProductCollection;
import store.validator.OrderValidator;

public record OrderRequest(String order, ProductCollection productCollection) {
    public OrderRequest {
        OrderValidator.validateFormat(order);
        OrderValidator.validateProduct(order, productCollection);
    }
}
