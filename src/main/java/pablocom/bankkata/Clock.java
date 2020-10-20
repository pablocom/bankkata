package pablocom.bankkata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String todayAsString() {
        LocalDate today = getToday();
        return today.format(dateTimeFormatter);
    }

    protected LocalDate getToday() {
        return LocalDate.now();
    }
}
