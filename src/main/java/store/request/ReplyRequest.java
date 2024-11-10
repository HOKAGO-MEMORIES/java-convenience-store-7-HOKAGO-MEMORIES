package store.request;

import store.validator.ReplyValidator;

public record ReplyRequest(String reply) {
    public ReplyRequest {
        ReplyValidator.validateReply(reply);
    }
}
