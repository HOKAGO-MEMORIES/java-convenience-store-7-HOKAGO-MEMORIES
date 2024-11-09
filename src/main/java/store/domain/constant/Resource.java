package store.domain.constant;

public enum Resource {
    PROMOTIONS_FILE("promotions.md"),
    PRODUCTS_FILE("products.md");

    private final String fileName;

    Resource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
