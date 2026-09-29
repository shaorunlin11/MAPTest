package com.zappos.json.format;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class BigDecimalFormattercastTest {
    @Test
    public void testCastWithBigDecimal() {
        BigDecimalFormatter formatter = new BigDecimalFormatter();
        BigDecimal input = new BigDecimal("123.45");
        BigDecimal result = formatter.cast(input);
        assertEquals(input, result);
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithNonBigDecimal() {
        BigDecimalFormatter formatter = new BigDecimalFormatter();
        String input = "123.45";
        formatter.cast(input);
    }

    public void testCastWithNull() {
        BigDecimalFormatter formatter = new BigDecimalFormatter();
        BigDecimal result = formatter.cast(null);
        assertNull(result);
    }
}
