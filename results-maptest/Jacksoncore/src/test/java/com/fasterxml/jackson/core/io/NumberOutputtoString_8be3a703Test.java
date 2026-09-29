package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputtoString_8be3a703Test {

    @Test
    public void testToStringFloat() {
        assertEquals("0.0", NumberOutput.toString(0.0f));
        assertEquals("1.5", NumberOutput.toString(1.5f));
        assertEquals("-2.3", NumberOutput.toString(-2.3f));
        assertEquals("NaN", NumberOutput.toString(Float.NaN));
        assertEquals("Infinity", NumberOutput.toString(Float.POSITIVE_INFINITY));
        assertEquals("-Infinity", NumberOutput.toString(Float.NEGATIVE_INFINITY));
    }
}
