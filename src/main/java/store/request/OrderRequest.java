package store.request;

import store.domain.ProductCollection;
import store.validator.OrderValidator;

public class OrderRequest {
    public OrderRequest(String order, ProductCollection productCollection) {
        OrderValidator.validateFormat(order);
        OrderValidator.validateProduct(order, productCollection);
    }
}
