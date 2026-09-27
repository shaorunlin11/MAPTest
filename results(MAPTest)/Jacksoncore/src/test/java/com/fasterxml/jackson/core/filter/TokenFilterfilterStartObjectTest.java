package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterfilterStartObjectTest {
    @Test
    public void testFilterStartObjectReturnsThis() {
        TokenFilter tokenFilter = new TokenFilter();
        TokenFilter result = tokenFilter.filterStartObject();
        assertSame("filterStartObject should return the same instance", tokenFilter, result);
    }
}
