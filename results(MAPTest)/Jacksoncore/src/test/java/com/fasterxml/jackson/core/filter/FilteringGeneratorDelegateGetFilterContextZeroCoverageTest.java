package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import com.fasterxml.jackson.core.JsonStreamContext;

public class FilteringGeneratorDelegateGetFilterContextZeroCoverageTest {
    @Test
    public void testGetFilterContext() throws Exception {
        // Create a FilteringGeneratorDelegate instance
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(null, null, false, false);

        // Call the method under test
        JsonStreamContext context = delegate.getFilterContext();

        // Assert that the method returns a non-null value
        assert context != null;
    }
}
