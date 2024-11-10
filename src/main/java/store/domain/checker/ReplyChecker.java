package store.domain.checker;

public class ReplyChecker {
    private final static String POSITIVE = "Y";

    public static boolean checkReply(String reply) {
        return reply.equals(POSITIVE);
    }
}
