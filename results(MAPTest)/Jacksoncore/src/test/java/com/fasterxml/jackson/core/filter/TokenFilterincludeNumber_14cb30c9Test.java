package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterincludeNumber_14cb30c9Test {
    @Test
    public void testIncludeNumber() throws Exception {
        // Create a subclass with a known implementation of _includeScalar
        TokenFilter testFilter = new TokenFilter() {
            protected boolean _includeScalar() {
                return true;
            }
        };

        // Test with a double value
        boolean result = testFilter.includeNumber(123.45);
        assertTrue("includeNumber should return true", result);
    }
}
