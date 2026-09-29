package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.FilteringGeneratorDelegate;

public class FilteringGeneratorDelegategetFilterTest {
    @Test
    public void testGetFilterReturnsRootFilter() throws Exception {
        // Create a mock TokenFilter
        TokenFilter mockFilter = new TokenFilter() {
            public boolean include(JsonParser p) {
                return false;
            }
        };

        // Create a FilteringGeneratorDelegate with the mock filter
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(null, mockFilter, false, false);

        // Call getFilter and verify it returns the expected value
        TokenFilter result = delegate.getFilter();
        assertNotNull("getFilter should not return null", result);
        assertEquals("getFilter should return the same TokenFilter instance", mockFilter, result);
    }

    @Test
    public void testGetFilterWhenRootFilterIsNotSet() throws Exception {
        // Create a FilteringGeneratorDelegate without setting rootFilter
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(null, null, false, false);

        // Call getFilter and verify it returns null
        TokenFilter result = delegate.getFilter();
        assertNull("getFilter should return null when rootFilter is not set", result);
    }
}
