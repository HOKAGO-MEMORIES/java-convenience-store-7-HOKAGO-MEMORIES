package store.validator;

import store.util.ExceptionMessages;

public class ReplyValidator {
    private static final String REPLY_FORMAT = "^[YN]$";

    public static void validateReply(String reply) {
        if (!reply.matches(REPLY_FORMAT)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_REPLY.getMessage());
        }
    }
}
