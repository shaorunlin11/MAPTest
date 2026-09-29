package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterincludeRootValueTest {
    @Test
    public void testIncludeRootValueReturnsThis() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeRootValue(0);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeRootValueDoesNotModifyState() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeRootValue(123);
        assertSame(filter, result);
    }
}
