package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.ValueFormatter;

public class BigDecimalFormatternewInstanceTest {
    @Test
    public void testNewInstanceReturnsBigDecimalFormatter() {
        BigDecimalFormatter formatter = new BigDecimalFormatter();
        ValueFormatter<BigDecimal> result = formatter.newInstance();
        assertTrue(result instanceof BigDecimalFormatter);
    }

    @Test
    public void testNewInstanceReturnsValueFormatterImplementation() {
        BigDecimalFormatter formatter = new BigDecimalFormatter();
        ValueFormatter<BigDecimal> result = formatter.newInstance();
        assertTrue(result instanceof ValueFormatter);
    }
}
