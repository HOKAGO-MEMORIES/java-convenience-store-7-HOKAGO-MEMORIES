package store.validator;

import store.domain.ProductCollection;
import store.util.ExceptionMessages;

public class OrderValidator {

    private static final String DELIMITER = ",";
    private static final String HYPHEN = "-";
    private static final String CORRECT_FORMAT = "\\[[가-힣]+-\\d+\\](,\\[[가-힣]+-\\d+\\])*";

    public static void validateFormat(String order) {
        if (!order.matches(CORRECT_FORMAT)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FORMAT.getMessage());
        }
    }

    public static void validateProduct(String order, ProductCollection productCollection) {
        String[] orders = order.split(DELIMITER);
        for (String productOrder : orders) {
            productOrder = productOrder.replace("[", "").replace("]", "");
            String[] productPart = productOrder.split(HYPHEN);
            String productName = productPart[0];
            int orderQuantity = Integer.parseInt(productPart[1]);

            validateProductName(productName, productCollection);
            validateQuantity(productName, orderQuantity, productCollection);
        }
    }

    private static void validateProductName(String productName, ProductCollection productCollection) {
        if (!productCollection.containsProduct(productName)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRODUCT.getMessage());
        }
    }

    private static void validateQuantity(String productName, int quantity, ProductCollection productCollection) {
        if (!productCollection.hasEnoughQuantity(productName, quantity)) {
            throw new IllegalArgumentException(ExceptionMessages.EXCEED_QUANTITY.getMessage());
        }
    }
}
