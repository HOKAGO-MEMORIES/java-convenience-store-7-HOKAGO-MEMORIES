package store.view.input;

import camp.nextstep.edu.missionutils.Console;
import store.domain.ProductCollection;
import store.request.OrderRequest;
import store.request.ReplyRequest;
import store.util.ExceptionMessages;

public class InputView {
    public OrderRequest readOrder(ProductCollection productCollection) {
        while (true) {
            try {
                String order = Console.readLine();
                return new OrderRequest(order, productCollection);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (Exception exception) {
                System.out.println(ExceptionMessages.INVALID_INPUT.getMessage());
            }
        }
    }

    public ReplyRequest readReply() {
        while (true) {
            try {
                String reply = Console.readLine();
                return new ReplyRequest(reply);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

}