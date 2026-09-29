package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFiltertoStringTest {
    @Test
    public void testToStringForIncludeAll() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        assertEquals("TokenFilter.INCLUDE_ALL", filter.toString());
    }

    @Test
    public void testToStringForOtherInstances() {
        TokenFilter filter = new TokenFilter();
        String result = filter.toString();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
