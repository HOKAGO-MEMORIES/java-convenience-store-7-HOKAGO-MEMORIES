package store.service;

import java.time.LocalDate;
import java.util.List;
import store.domain.Promotion;
import store.domain.PromotionCollection;
import store.domain.constant.Resource;
import store.service.reader.MarkdownReader;

public class PromotionService {
    private static final String DELIMITER = ",";
    private final MarkdownReader reader;

    public PromotionService(MarkdownReader reader) {
        this.reader = reader;
    }

    public PromotionCollection setPromotionCollection() {
        PromotionCollection promotionCollection = new PromotionCollection();

        for (String line : readLines()) {
            String[] tokens = line.split(DELIMITER);
            promotionCollection.addPromotion(loadPromotion(tokens));
        }
        return promotionCollection;
    }

    private List<String> readLines() {
        return reader.readResource(Resource.PROMOTIONS_FILE);
    }

    private Promotion loadPromotion(String[] tokens) {
        String name = tokens[0];
        int buy = Integer.parseInt(tokens[1]);
        int get = Integer.parseInt(tokens[2]);
        LocalDate startDate = LocalDate.parse(tokens[3]);
        LocalDate endDate = LocalDate.parse(tokens[4]);

        return new Promotion(name, buy, get, startDate, endDate);
    }
}
