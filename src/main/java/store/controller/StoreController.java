package store.controller;

import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.service.InitStoreService;
import store.view.input.InputView;
import store.view.output.OutputView;

public class StoreController {

    private final InputView inputView;
    private final OutputView outputView;

    public StoreController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        PromotionCollection promotionCollection = InitStoreService.initPromotionCollection();
        ProductCollection productCollection = InitStoreService.initProductCollection(promotionCollection);
        outputView.printWelcome();
        outputView.printProductCollection(productCollection);
        outputView.printPurchase();
    }
}
