package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputtoString_29b7a6b6Test {

    @Test
    public void testToString() {
        assertEquals("0.0", NumberOutput.toString(0.0));
        assertEquals("1.5", NumberOutput.toString(1.5));
        assertEquals("-3.14", NumberOutput.toString(-3.14));
        assertEquals("Infinity", NumberOutput.toString(Double.POSITIVE_INFINITY));
        assertEquals("-Infinity", NumberOutput.toString(Double.NEGATIVE_INFINITY));
        assertTrue(Double.isNaN(Double.parseDouble(NumberOutput.toString(Double.NaN))));
    }
}
