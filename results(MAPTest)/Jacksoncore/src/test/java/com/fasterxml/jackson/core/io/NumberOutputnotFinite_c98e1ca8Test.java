package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputnotFinite_c98e1ca8Test {

    @Test
    public void testNotFiniteWithNaN() {
        assertTrue(NumberOutput.notFinite(Float.NaN));
    }

    @Test
    public void testNotFiniteWithPositiveInfinity() {
        assertTrue(NumberOutput.notFinite(Float.POSITIVE_INFINITY));
    }

    @Test
    public void testNotFiniteWithNegativeInfinity() {
        assertTrue(NumberOutput.notFinite(Float.NEGATIVE_INFINITY));
    }

    @Test
    public void testNotFiniteWithFiniteValue() {
        assertFalse(NumberOutput.notFinite(0.0f));
        assertFalse(NumberOutput.notFinite(1.5f));
        assertFalse(NumberOutput.notFinite(-3.14f));
    }
}
