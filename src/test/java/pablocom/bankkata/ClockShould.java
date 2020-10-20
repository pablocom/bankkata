package pablocom.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClockShould {
    @Test
    public void return_todays_date_in_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();

        String date = clock.todayAsString();

        assertThat(date, is("20/09/1996"));
    }

    private class TestableClock extends Clock {
        @Override
        protected LocalDate getToday() {
            return LocalDate.of(1996, 9, 20);
        }
    }
}