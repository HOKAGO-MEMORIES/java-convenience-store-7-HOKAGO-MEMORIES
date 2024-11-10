package store.controller;

import store.domain.OrderItemsCollection;
import store.domain.ProductCollection;
import store.extractor.OrderExtractor;
import store.request.OrderRequest;
import store.view.input.InputView;
import store.view.output.OutputView;

public class PurchaseController {

    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();

    public PurchaseController(ProductCollection productCollection) {
        printWelcome(productCollection);
    }

    private void printWelcome(ProductCollection productCollection) {
        outputView.printWelcome();
        outputView.printProductCollection(productCollection);
        outputView.printPurchase();
    }

    public OrderItemsCollection createOrderItemsCollection(ProductCollection productCollection) {
        OrderRequest request = inputView.readOrder(productCollection);
        return OrderExtractor.extractOrder(request.order());
    }
}
