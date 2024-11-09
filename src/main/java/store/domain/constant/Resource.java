package store.domain.constant;

public enum Resource {
    PRODUCTS_FILE("resources/products.md"),
    PROMOTIONS_FILE("resources/promotions.md");

    private final String filePath;

    Resource(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
