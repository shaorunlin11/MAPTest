package com.zappos.json.format;

import org.junit.Test;
import org.junit.Assert;

public class NoOpValueFormattercastTest {
    @Test
    public void testCast_returnsSameObject() {
        NoOpValueFormatter formatter = new NoOpValueFormatter();
        Object testObject = new Object();
        Assert.assertSame(testObject, formatter.cast(testObject));
    }

    @Test
    public void testCast_withNull_returnsNull() {
        NoOpValueFormatter formatter = new NoOpValueFormatter();
        Assert.assertNull(formatter.cast(null));
    }

    @Test
    public void testCast_withDifferentTypes_returnsSameInstance() {
        NoOpValueFormatter formatter = new NoOpValueFormatter();
        String stringObj = "test";
        Integer intObj = 123;
        Boolean boolObj = true;

        Assert.assertSame(stringObj, formatter.cast(stringObj));
        Assert.assertSame(intObj, formatter.cast(intObj));
        Assert.assertSame(boolObj, formatter.cast(boolObj));
    }
}
