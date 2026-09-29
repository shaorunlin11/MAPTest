package com.fasterxml.jackson.core.filter;

import org.junit.Test;

public class FilteringParserDelegateGetMatchCountZeroCoverageTest {
    @Test
    public void testGetMatchCount() throws Exception {
        // Create an instance of FilteringParserDelegate with _matchCount initialized
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        delegate._matchCount = 5; // Direct field access to satisfy requirement

        // Call the method under test
        int matchCount = delegate.getMatchCount();

        // Assert that the method returns the expected value
        assert matchCount == 5;
    }
}
