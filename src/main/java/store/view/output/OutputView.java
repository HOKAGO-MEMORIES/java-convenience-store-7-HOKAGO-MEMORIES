package store.view.output;

import store.util.StoreMessages;

public class OutputView {
    public void printWelcome() {
        System.out.println(StoreMessages.WELCOME.getMessage());
    }

    public void printPurchase() {
        System.out.println(StoreMessages.INPUT_PURCHASE.getMessage());
    }

    public void printNoPromotion(String name, int amount) {
        System.out.println(StoreMessages.NO_PROMOTION.getMessage(name, amount));
    }

    public void printCanPromotion(String name, int amount) {
        System.out.println(StoreMessages.CAM_PROMOTION.getMessage(name, amount));
    }

    public void printMembership() {
        System.out.println(StoreMessages.ASK_MEMBERSHIP.getMessage());
    }

    public void printRepurchase() {
        System.out.println(StoreMessages.ASK_REPURCHASE.getMessage());
    }
}
