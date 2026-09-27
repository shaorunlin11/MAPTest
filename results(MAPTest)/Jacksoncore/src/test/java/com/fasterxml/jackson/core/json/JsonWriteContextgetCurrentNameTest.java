package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriteContextgetCurrentNameTest {

    @Test
    public void testGetCurrentName_ReturnsCurrentName() throws Exception {
        // Arrange
        JsonWriteContext context = new JsonWriteContext(0, null, null);
        context._currentName = "testName";

        // Act
        String result = context.getCurrentName();

        // Assert
        assertEquals("testName", result);
    }

    @Test
    public void testGetCurrentName_ReturnsNullWhenNotSet() throws Exception {
        // Arrange
        JsonWriteContext context = new JsonWriteContext(0, null, null);

        // Act
        String result = context.getCurrentName();

        // Assert
        assertNull(result);
    }
}
