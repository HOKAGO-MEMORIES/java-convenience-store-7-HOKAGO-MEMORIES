package store.util;

public enum ReceiptFormat {
    STORE_HEADER("==============W 편의점================", 50),
    ITEM("상품명", 15),
    QUANTITY("수량", 15),
    PRICE("금액", 15),
    PROMOTION_HEADER("=============증\t\t정===============", 50),
    FREE_ITEM("증정", 15),
    SEPARATOR("====================================", 50),
    TOTAL_PAYMENT("총구매액", 12),
    PROMOTION_DISCOUNT("행사할인", 26),
    MEMBERSHIP_DISCOUNT("멤버십할인", 26),
    FINAL_PAYMENT("내실돈", 26);

    private final String label;
    private final int width;

    ReceiptFormat(String label, int width) {
        this.label = label;
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public int getWidth() {
        return width;
    }

    public static String formatValue(String value, int width) {
        StringBuilder formatedLabel = new StringBuilder(value);
        while (formatedLabel.length() < width) {
            formatedLabel.append(" ");
        }
        return formatedLabel.toString();
    }

    public static String getFormatedLabel(ReceiptFormat format) {
        return formatValue(format.getLabel(), format.getWidth());
    }

    public static void printStoreHeader() {
        System.out.println(formatValue(STORE_HEADER.getLabel(), STORE_HEADER.getWidth()));
    }

    public static void printPromotionHeader() {
        System.out.println(formatValue(PROMOTION_HEADER.getLabel(), PROMOTION_HEADER.getWidth()));
    }

    public static void printSeparator() {
        System.out.println(formatValue(SEPARATOR.getLabel(), SEPARATOR.getWidth()));
    }
}
