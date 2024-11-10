package store;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.config.AppInitializer;
import store.domain.ProductCollection;
import store.domain.PromotionCollection;
import store.validator.OrderValidator;

public class OrderValidatorTest {

    private ProductCollection productCollection;

    @BeforeEach
    void setUp() {
        PromotionCollection promotionCollection = AppInitializer.initPromotionCollection();
        this.productCollection = AppInitializer.initProductCollection(promotionCollection);
    }

    @Test
    void 정상적인_형식() {
        String validOrder = "[콜라-2],[사이다-3]";
        assertDoesNotThrow(() -> OrderValidator.validateFormat(validOrder));
    }

    @Test
    void 쉼표_없이_입력() {
        String invalidOrder = "[콜라-2][사이다-3]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateFormat(invalidOrder));
    }

    @Test
    void 하이픈_없이_입력() {
        String invalidOrder = "[콜라2],[사이다-3]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateFormat(invalidOrder));
    }

    @Test
    void 상품명_없이_입력() {
        String invalidOrder = "[2],[사이다-3]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateFormat(invalidOrder));
    }

    @Test
    void 수량_없이_입력() {
        String invalidOrder = "[콜라-2],[사이다-]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateFormat(invalidOrder));
    }

    @Test
    void 한글이_아닌_제품명_입력() {
        String invalidOrder = "[coca-2],[사이다-1]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateFormat(invalidOrder));
    }

    @Test
    void 정상적인_상품과_수량() {
        String order = "[콜라-2],[사이다-3]";
        assertDoesNotThrow(() -> OrderValidator.validateProduct(order, productCollection));
    }

    @Test
    void 정상적인_프로모션_상품과_수량() {
        String order = "[콜라-18],[초코바-8]";
        assertDoesNotThrow(() -> OrderValidator.validateProduct(order, productCollection));
    }

    @Test
    void 존재하지_않는_상품_입력() {
        String order = "[밀키스-2]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateProduct(order, productCollection));
    }

    @Test
    void 재고보다_많은_수량_입력() {
        String order = "[오렌지주스-10]";
        assertThrows(IllegalArgumentException.class, () -> OrderValidator.validateProduct(order, productCollection));
    }
}
