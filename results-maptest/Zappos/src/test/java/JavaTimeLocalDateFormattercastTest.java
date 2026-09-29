package com.zappos.json.format;

import org.junit.Test;
import java.time.LocalDate;

public class JavaTimeLocalDateFormattercastTest {
    @Test
    public void testCastWithValidLocalDate() {
        JavaTimeLocalDateFormatter formatter = new JavaTimeLocalDateFormatter();
        LocalDate date = LocalDate.now();
        LocalDate result = formatter.cast(date);
        assert result == date;
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithInvalidObject() {
        JavaTimeLocalDateFormatter formatter = new JavaTimeLocalDateFormatter();
        String invalidObject = "invalid";
        formatter.cast(invalidObject);
    }
}
