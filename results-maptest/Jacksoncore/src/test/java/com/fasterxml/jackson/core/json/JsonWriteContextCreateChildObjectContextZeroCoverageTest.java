package com.fasterxml.jackson.core.json;

import org.junit.Test;

public class JsonWriteContextCreateChildObjectContextZeroCoverageTest {
    @Test
    public void testCreateChildObjectContextWithNullChildAndDups() throws Exception {
        // Create a JsonWriteContext instance with _child null and _dups null
        JsonWriteContext context = new JsonWriteContext(0, null, null);

        // Call the method under test
        JsonWriteContext result = context.createChildObjectContext();

        // Assert that the result is not null
        assert result != null;

        // Assert that the _child field of the original context is now set to the result
        assert context._child == result;

        // Assert that the result's _parent is the original context
        assert result._parent == context;

        // Assert that the result's _dups is null (since _dups was null in the original context)
        assert result._dups == null;
    }
}
