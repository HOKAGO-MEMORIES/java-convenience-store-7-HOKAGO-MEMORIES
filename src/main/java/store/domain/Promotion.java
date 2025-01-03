package store.domain;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(final String name, final int buy, final int get, final LocalDate startDate,
                     final LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean hasName(final String promotionName) {
        return this.name.equals(promotionName);
    }

    public boolean isActive(LocalDate currentDate) {
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }
}
