package store.controller;

import java.util.List;
import store.domain.OrderItems;
import store.domain.OrderItemsCollection;
import store.domain.Product;
import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.domain.checker.ReplyChecker;
import store.request.ReplyRequest;
import store.view.input.InputView;
import store.view.output.OutputView;

public class PromotionController {
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();
    private final ProductCollection productCollection;
    private final PromotionCollection promotionCollection;
    private final static int TWO_PLUS_ONE = 3;
    private final static int TWO_PLUS_ONE_BUY = 2;
    private final static int TWO_PLUS_ONE_GET = 1;
    private final static int ONE_PLUS_ONE = 2;
    private final static int ONE_PLUS_ONE_BUY = 1;
    private final static int ONE_PLUS_ONE_GET = 1;

    public PromotionController(ProductCollection productCollection, PromotionCollection promotionCollection) {
        this.productCollection = productCollection;
        this.promotionCollection = promotionCollection;
    }

    public void applyPromotion(OrderItemsCollection orderItemsCollection) {
        List<OrderItems> orderItems = orderItemsCollection.getOrderItems();

        for (OrderItems orderItem : orderItems) {
            List<Product> products = productCollection.getProduct(orderItem.getProductName());
            for (Product product : products) {
                checkPromotion(product, orderItem.getOrderQuantity());
            }
        }
    }

    private int checkPromotion(Product product, int orderQuantity) {
        if (product.hasPromotion()) {
            String promotionName = product.getPromotionName();
            if (promotionCollection.checkTwoPlusOne(promotionName)) {
                return applyTwoPlusOne(product, orderQuantity);
            }
            if (promotionCollection.checkOnePlusOne(promotionName)) {
                return applyOnePlusOne(product, orderQuantity);
            }
        }
        product.changeQuantity(product.getQuantity() - orderQuantity);
        return orderQuantity;
    }

    private int applyTwoPlusOne(Product product, int orderQuantity) {
        int maxPromotionAmount = (product.getQuantity() / TWO_PLUS_ONE) * TWO_PLUS_ONE;
        if (orderQuantity > maxPromotionAmount) {
            if (askFullPriceItems(product.getName(), orderQuantity - maxPromotionAmount)) {
                orderQuantity -= product.getQuantity();
                product.changeQuantity(0);
                return orderQuantity;
            }
            orderQuantity -= maxPromotionAmount;
            product.changeQuantity(product.getQuantity() - maxPromotionAmount);
            return orderQuantity;
        }
        if (orderQuantity % TWO_PLUS_ONE == TWO_PLUS_ONE_BUY) {
            if (askAdditionalPromotionItems(product.getName(), TWO_PLUS_ONE_GET)) {
                product.changeQuantity(product.getQuantity() - maxPromotionAmount);
                return 0;
            }
            product.changeQuantity(product.getQuantity() - orderQuantity);
            return 0;
        }
        product.changeQuantity(product.getQuantity() - orderQuantity);
        return 0;
    }

    private int applyOnePlusOne(Product product, int orderQuantity) {
        int maxPromotionAmount = (product.getQuantity() / ONE_PLUS_ONE) * ONE_PLUS_ONE;
        if (orderQuantity > maxPromotionAmount) {
            if (askFullPriceItems(product.getName(), orderQuantity - maxPromotionAmount)) {
                orderQuantity -= product.getQuantity();
                product.changeQuantity(0);
                return orderQuantity;
            }
            orderQuantity -= maxPromotionAmount;
            product.changeQuantity(product.getQuantity() - maxPromotionAmount);
            return orderQuantity;
        }
        if (orderQuantity % ONE_PLUS_ONE == ONE_PLUS_ONE_BUY) {
            if (askAdditionalPromotionItems(product.getName(), ONE_PLUS_ONE_GET)) {
                product.changeQuantity(product.getQuantity() - maxPromotionAmount);
                return 0;
            }
            product.changeQuantity(product.getQuantity() - orderQuantity);
            return 0;
        }
        product.changeQuantity(product.getQuantity() - orderQuantity);
        return 0;
    }

    private boolean askAdditionalPromotionItems(String productName, int orderQuantity) {
        outputView.printCanPromotion(productName, orderQuantity);
        ReplyRequest request = inputView.readReply();
        return ReplyChecker.checkReply(request.reply());
    }

    private boolean askFullPriceItems(String name, int amount) {
        outputView.printNoPromotion(name, amount);
        ReplyRequest request = inputView.readReply();
        return ReplyChecker.checkReply(request.reply());
    }
}
