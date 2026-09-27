package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterincludeStringTest {
    @Test
    public void testIncludeStringDelegatesToIncludeScalar() throws Exception {
        // Create a subclass to access protected method
        TokenFilter testInstance = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return true;
            }
        };

        boolean result = testInstance.includeString("test");
        assertTrue(result);
    }
}
