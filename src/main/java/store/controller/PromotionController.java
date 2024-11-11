package store.controller;

import java.util.List;
import store.domain.OrderItems;
import store.domain.OrderItemsCollection;
import store.domain.Product;
import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.domain.Receipt;
import store.domain.checker.ReplyChecker;
import store.request.ReplyRequest;
import store.util.PromotionType;
import store.view.input.InputView;
import store.view.output.OutputView;

public class PromotionController {
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();
    private final ProductCollection productCollection;
    private final PromotionCollection promotionCollection;
    private final Receipt receipt;

    public PromotionController(ProductCollection productCollection, PromotionCollection promotionCollection) {
        this.productCollection = productCollection;
        this.promotionCollection = promotionCollection;
        this.receipt = new Receipt();
    }

    public Receipt startPromotion(OrderItemsCollection orderItemsCollection) {
        List<OrderItems> orderItems = orderItemsCollection.getOrderItems();

        for (OrderItems orderItem : orderItems) {
            List<Product> products = productCollection.getProduct(orderItem.getProductName());
            for (Product product : products) {
                int remainingQuantity = checkPromotion(product, orderItem.getOrderQuantity());
                OrderItems purchasedItem = new OrderItems(product.getName(), remainingQuantity,
                        product.getPrice() * remainingQuantity);
                receipt.addPurchasedItem(purchasedItem);
            }
        }
        return receipt;
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
        return applyPromotion(product, orderQuantity, PromotionType.TWO_PLUS_ONE);
    }

    private int applyOnePlusOne(Product product, int orderQuantity) {
        return applyPromotion(product, orderQuantity, PromotionType.ONE_PLUS_ONE);
    }

    private int applyPromotion(Product product, int orderQuantity, PromotionType promotionType) {
        int promotionGroupSize = promotionType.getPromotionGroupSize();
        int buyQuantity = promotionType.getBuyQuantity();
        int freeQuantity = promotionType.getFreeQuantity();

        int maxPromotionAmount = (product.getQuantity() / promotionGroupSize) * promotionGroupSize;

        if (orderQuantity > maxPromotionAmount) {
            int fullPriceItems = orderQuantity - maxPromotionAmount;

            if (askFullPriceItems(product.getName(), fullPriceItems)) {
                product.changeQuantity(0);
                getFreeItem(product.getName(), freeQuantity, product.getPrice());
                return fullPriceItems;
            }
            product.changeQuantity(product.getQuantity() - maxPromotionAmount);
            getFreeItem(product.getName(), freeQuantity, product.getPrice());
            return orderQuantity - maxPromotionAmount;
        }

        if (orderQuantity % promotionGroupSize == buyQuantity) {
            if (askAdditionalPromotionItems(product.getName(), freeQuantity)) {
                getFreeItem(product.getName(), freeQuantity, product.getPrice());
            }
        }

        getFreeItem(product.getName(), freeQuantity, product.getPrice());
        product.changeQuantity(product.getQuantity() - orderQuantity);
        return 0;
    }

    private void getFreeItem(String productName, int orderQuantity, int price) {
        OrderItems freeItem = new OrderItems(productName, orderQuantity, price);
        receipt.addFreeItem(freeItem);
        receipt.applyPromotionDiscount(freeItem.getPrice());
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
