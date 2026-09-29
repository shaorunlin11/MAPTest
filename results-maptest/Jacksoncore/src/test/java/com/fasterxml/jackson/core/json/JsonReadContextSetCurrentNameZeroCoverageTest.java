package com.fasterxml.jackson.core.json;

import org.junit.Test;

public class JsonReadContextSetCurrentNameZeroCoverageTest {
    @Test
    public void testSetCurrentName() throws Exception {
        // Create a JsonReadContext with a null _dups and a non-null _currentName
        JsonReadContext context = new JsonReadContext(null, null, 0, 0, 0);
        context._currentName = "existingName";

        // Call setCurrentName with a non-null name
        context.setCurrentName("newName");

        // Verify that _currentName was updated
        assert context._currentName.equals("newName");
    }
}
