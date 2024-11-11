package store.controller;

import store.domain.checker.ReplyChecker;
import store.request.ReplyRequest;
import store.view.input.InputView;
import store.view.output.OutputView;

public class RepurchaseController {
    private final static OutputView outputView = new OutputView();
    private final static InputView inputView = new InputView();

    public boolean askRepurchase() {
        outputView.printRepurchase();
        ReplyRequest request = inputView.readReply();
        return ReplyChecker.checkReply(request.reply());
    }
}
