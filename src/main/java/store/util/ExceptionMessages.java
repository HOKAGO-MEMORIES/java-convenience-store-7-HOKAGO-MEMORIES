package store.util;

public enum ExceptionMessages {
    FILE_NOT_FOUND("파일 이름을 찾을 수 없습니다: %s");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage(String file) {
        return String.format("ERROR: %s", String.format(message, file));
    }
}
