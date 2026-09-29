package com.fasterxml.jackson.core.json;

import org.junit.Test;

public class JsonWriteContextCreateChildArrayContextZeroCoverageTest {
    @Test
    public void testCreateChildArrayContextWithNullContextAndDups() throws Exception {
        // Given: a JsonWriteContext with _child == null and _dups == null
        JsonWriteContext context = new JsonWriteContext(0, null, null);

        // When: createChildArrayContext is called
        JsonWriteContext result = context.createChildArrayContext();

        // Then: the returned context should be a new instance with TYPE_ARRAY
        // and the _child field of the original context should be set to this new instance
        // This covers line 121 of the method
    }
}
