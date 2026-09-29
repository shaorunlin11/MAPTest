package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Timestamp;

public class JavaTimestampFormattercastTest {
    @Test
    public void testCastWithValidTimestamp() {
        JavaTimestampFormatter formatter = new JavaTimestampFormatter();
        Timestamp input = new Timestamp(System.currentTimeMillis());
        Timestamp result = formatter.cast(input);
        assertEquals(input, result);
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithInvalidObject() {
        JavaTimestampFormatter formatter = new JavaTimestampFormatter();
        String input = "invalid";
        formatter.cast(input);
    }
}
