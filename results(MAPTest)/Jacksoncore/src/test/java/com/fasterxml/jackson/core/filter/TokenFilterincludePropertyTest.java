package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterincludePropertyTest {
    @Test
    public void testIncludePropertyReturnsThis() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeProperty("test");
        assertSame("includeProperty should return the same instance", filter, result);
    }

    @Test
    public void testIncludePropertyWithNullName() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.includeProperty(null);
        assertSame("includeProperty should return the same instance even with null name", filter, result);
    }
}
