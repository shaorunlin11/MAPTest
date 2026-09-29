package com.zappos.json.format;

import java.math.BigInteger;
import org.junit.Test;
import org.junit.Assert;

public class BigIntegerFormattercastTest {
    @Test
    public void testCastWithBigInteger() {
        BigIntegerFormatter formatter = new BigIntegerFormatter();
        Object obj = new BigInteger("12345");
        BigInteger result = formatter.cast(obj);
        Assert.assertEquals(new BigInteger("12345"), result);
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithNonBigInteger() {
        BigIntegerFormatter formatter = new BigIntegerFormatter();
        Object obj = "12345";
        formatter.cast(obj);
    }
}
