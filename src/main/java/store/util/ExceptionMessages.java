package store.util;

public enum ExceptionMessages {
    FILE_NOT_FOUND("파일 이름을 찾을 수 없습니다: %s"),
    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다."),
    INVALID_PRODUCT("존재하지 않는 상품입니다."),
    EXCEED_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다."),
    INVALID_INPUT("잘못된 입력입니다.");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage(String file) {
        return String.format("ERROR: %s", String.format(message, file));
    }

    public String getMessage() {
        return String.format("ERROR: %s 다시 입력해 주세요.", message);
    }
}
