package com.fasterxml.jackson.core.filter;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
public class TokenFilterContextskipParentChecksTest {
    @Test
    public void testSkipParentChecksWithNoParent() throws Exception {
        // Create a TokenFilterContext with no parent
        TokenFilterContext context = new TokenFilterContext(0, null, new TokenFilter(), false);

        // Set filter to non-null value for testing
        Field filterField = TokenFilterContext.class.getDeclaredField("_filter");
        filterField.setAccessible(true);
        filterField.set(context, new TokenFilter());

        // Call method under test
        context.skipParentChecks();

        // Verify that _filter is null
        assertEquals("Filter should be null after skipParentChecks", null, filterField.get(context));
    }

    @Test
    public void testSkipParentChecksWithSingleParent() throws Exception {
        // Create parent context
        TokenFilterContext parent = new TokenFilterContext(0, null, new TokenFilter(), false);

        // Create child context with parent
        TokenFilterContext context = new TokenFilterContext(0, parent, new TokenFilter(), false);

        // Set filters to non-null values for testing
        Field filterField = TokenFilterContext.class.getDeclaredField("_filter");
        filterField.setAccessible(true);
        filterField.set(context, new TokenFilter());
        filterField.set(parent, new TokenFilter());

        // Call method under test
        context.skipParentChecks();

        // Verify that both context and parent have null filters
        assertNull("Filter should be null after skipParentChecks", filterField.get(context));
        assertNull("Parent filter should be null after skipParentChecks", filterField.get(parent));
    }
}
