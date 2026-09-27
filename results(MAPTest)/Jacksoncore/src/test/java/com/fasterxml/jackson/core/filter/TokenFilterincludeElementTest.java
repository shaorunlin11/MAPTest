package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterincludeElementTest {
    @Test
    public void testIncludeElementReturnsThis() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeElement(0);
        assertSame("includeElement should return the same instance", filter, result);
    }

    @Test
    public void testIncludeElementWithDifferentIndexReturnsThis() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeElement(123);
        assertSame("includeElement should return the same instance", filter, result);
    }
}
