package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoOpValueFormatternewInstanceTest {
    @Test
    public void testNewInstance_ThrowsUnsupportedOperationException() {
        NoOpValueFormatter formatter = new NoOpValueFormatter();
        try {
            formatter.newInstance();
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }
}
