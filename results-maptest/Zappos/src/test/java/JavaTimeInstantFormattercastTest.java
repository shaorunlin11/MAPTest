package com.zappos.json.format;

import org.junit.Test;
import org.junit.Assert;
import java.time.Instant;

public class JavaTimeInstantFormattercastTest {
    @Test
    public void testCastWithValidInstant() {
        JavaTimeInstantFormatter formatter = new JavaTimeInstantFormatter();
        Instant validInstant = Instant.now();
        Assert.assertEquals(validInstant, formatter.cast(validInstant));
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithInvalidObject() {
        JavaTimeInstantFormatter formatter = new JavaTimeInstantFormatter();
        String invalidObject = "invalid";
        formatter.cast(invalidObject);
    }

    @Test
    public void testCastWithNull() {
        JavaTimeInstantFormatter formatter = new JavaTimeInstantFormatter();
        Assert.assertNull(formatter.cast(null));
    }
}
