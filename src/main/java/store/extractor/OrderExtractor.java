package store.extractor;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderExtractor {
    private static final String DELIMITER = ",";
    private static final String HYPHEN = "-";

    public static Map<String, Integer> extractOrder(String order) {
        String[] orders = order.split(DELIMITER);
        Map<String, Integer> orderItems = new LinkedHashMap<String, Integer>();

        for (String productOrder : orders) {
            productOrder = productOrder.replace("[", "").replace("]", "");
            String[] productPart = productOrder.split(HYPHEN);
            orderItems.put(productPart[0], Integer.parseInt(productPart[1]));
        }
        return orderItems;
    }
}
