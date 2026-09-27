package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterContexttoStringTest {

    @Test
    public void testToString() throws Exception {
        // Create a TokenFilterContext instance using the constructor
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);

        // Call toString method
        String result = context.toString();

        // Verify that the result is not null and not empty
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
