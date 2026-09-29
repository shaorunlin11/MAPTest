package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterfilterStartArrayTest {
    @Test
    public void testFilterStartArrayReturnsThis() {
        TokenFilter filter = new TokenFilter();
        TokenFilter result = filter.filterStartArray();
        assertSame("filterStartArray should return the same instance", filter, result);
    }
}
