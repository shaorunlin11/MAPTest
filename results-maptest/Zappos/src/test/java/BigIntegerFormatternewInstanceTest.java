package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.ValueFormatter;

public class BigIntegerFormatternewInstanceTest {
    @Test
    public void testNewInstanceReturnsBigIntegerFormatter() {
        BigIntegerFormatter formatter = new BigIntegerFormatter();
        ValueFormatter<BigInteger> result = formatter.newInstance();
        assertTrue(result instanceof BigIntegerFormatter);
    }

    @Test
    public void testNewInstanceImplementsValueFormatter() {
        BigIntegerFormatter formatter = new BigIntegerFormatter();
        ValueFormatter<BigInteger> result = formatter.newInstance();
        assertTrue(result instanceof ValueFormatter);
    }

    @Test
    public void testNewInstanceReturnsNonNull() {
        BigIntegerFormatter formatter = new BigIntegerFormatter();
        ValueFormatter<BigInteger> result = formatter.newInstance();
        assertNotNull(result);
    }
}
