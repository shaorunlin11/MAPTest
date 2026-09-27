package com.fasterxml.jackson.core.filter;

import org.junit.Test;

public class TokenFilterContextCloseArrayZeroCoverageTest {
    @Test
    public void testCloseArrayWithFilterNotIncludeAll() throws Exception {
        // Create a mock TokenFilter that is not INCLUDE_ALL
        TokenFilter mockFilter = new TokenFilter() {
            @Override
            public void filterFinishArray() {
                // Do nothing
            }

            @Override
            public TokenFilter clone() {
                return this;
            }
        };

        // Create a TokenFilterContext with _filter not null and not INCLUDE_ALL
        TokenFilterContext context = new TokenFilterContext(0, null, mockFilter, false);

        // Call the method under test
        context.closeArray(null);
    }
}
