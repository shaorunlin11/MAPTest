package com.fasterxml.jackson.core.filter;

import org.junit.Test;

public class FilteringParserDelegateGetFilterZeroCoverageTest {
    @Test
    public void testGetFilter() {
        // Create a FilteringParserDelegate instance with a non-null rootFilter
        TokenFilter rootFilter = new TokenFilter() {
            public boolean include(TokenFilterContext context) {
                return false;
            }
        };
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, rootFilter, false, false);

        // Call the method under test
        TokenFilter filter = delegate.getFilter();

        // Assert that the returned filter is not null
        assert filter != null;
    }
}
