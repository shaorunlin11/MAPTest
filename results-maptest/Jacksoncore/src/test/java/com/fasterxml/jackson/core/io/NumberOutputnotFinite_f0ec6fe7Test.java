package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

public class NumberOutputnotFinite_f0ec6fe7Test {
    @Test
    public void testNotFinite() {
        // Test NaN value
        Assert.assertTrue(Double.isNaN(Double.NaN));
        Assert.assertTrue(NumberOutput.notFinite(Double.NaN));

        // Test positive infinity
        Assert.assertTrue(Double.isInfinite(Double.POSITIVE_INFINITY));
        Assert.assertTrue(NumberOutput.notFinite(Double.POSITIVE_INFINITY));

        // Test negative infinity
        Assert.assertTrue(Double.isInfinite(Double.NEGATIVE_INFINITY));
        Assert.assertTrue(NumberOutput.notFinite(Double.NEGATIVE_INFINITY));

        // Test finite values
        Assert.assertFalse(NumberOutput.notFinite(0.0));
        Assert.assertFalse(NumberOutput.notFinite(1.5));
        Assert.assertFalse(NumberOutput.notFinite(-3.14));
        Assert.assertFalse(NumberOutput.notFinite(Double.MIN_VALUE));
        Assert.assertFalse(NumberOutput.notFinite(Double.MAX_VALUE));
    }
}
