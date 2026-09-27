package com.zappos.json.format;

import org.junit.Test;

public class JavaTimeLocalDateFormatterSetPatternZeroCoverageTest {
    @Test
    public void testSetPattern() {
        JavaTimeLocalDateFormatter formatter = new JavaTimeLocalDateFormatter();
        String pattern = "yyyy-MM-dd";
        formatter.setPattern(pattern);
    }
}
