package store.controller;

import store.domain.Receipt;
import store.domain.checker.ReplyChecker;
import store.request.ReplyRequest;
import store.view.input.InputView;
import store.view.output.OutputView;

public class MembershipController {
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();
    private final Receipt receipt;

    private static final double DISCOUNT_RATE = 0.3;
    private static final int MAX_DISCOUNT_AMOUNT = 8000;

    public MembershipController(Receipt receipt) {
        this.receipt = receipt;
    }

    public Receipt applyMembership() {
        if (askMembership()) {
            int totalAmount = receipt.getTotalAmount();
            int discountAmount = (int) (totalAmount * DISCOUNT_RATE);
            discountAmount = Math.min(discountAmount, MAX_DISCOUNT_AMOUNT);

            receipt.applyMembershipDiscount(discountAmount);
        }
        receipt.calculateFinalPayment();
        return receipt;
    }

    private boolean askMembership() {
        outputView.printMembership();
        ReplyRequest request = inputView.readReply();
        return ReplyChecker.checkReply(request.reply());
    }
}
