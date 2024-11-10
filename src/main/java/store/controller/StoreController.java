package store.controller;

import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.view.input.InputView;
import store.view.output.OutputView;

public class StoreController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PromotionCollection promotionCollection;
    private final ProductCollection productCollection;

    public StoreController(PromotionCollection promotionCollection, ProductCollection productCollection) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.promotionCollection = promotionCollection;
        this.productCollection = productCollection;
    }

    public void run() {
        outputView.printWelcome();
        outputView.printProductCollection(productCollection);
        outputView.printPurchase();
        inputView.readOrder(productCollection);
    }
}
