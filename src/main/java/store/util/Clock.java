package store.util;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;

public class Clock {
    public static LocalDate now() {
        return DateTimes.now().toLocalDate();
    }
}
