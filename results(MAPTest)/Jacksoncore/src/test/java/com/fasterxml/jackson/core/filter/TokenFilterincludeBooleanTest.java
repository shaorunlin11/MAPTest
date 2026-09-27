package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class TokenFilterincludeBooleanTest {
    @Test
    public void testIncludeBooleanDelegatesToIncludeScalar() throws Exception {
        TokenFilter filter = new TokenFilter() {
            protected boolean _includeScalar() {
                return true;
            }

            // Stubbed method to satisfy superclass requirements
            public BigDecimal getDecimalValue() {
                return null;
            }
        };

        boolean result = filter.includeBoolean(false);
        Assert.assertTrue(result);
    }
}
