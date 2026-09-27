package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class JsonReadContextgetCurrentNameTest {

    @Test
    public void testGetCurrentName() throws Exception {
        // Create a JsonReadContext instance with a known current name
        JsonReadContext context = new JsonReadContext(null, null, 0, 0, 0);
        String expectedName = "testName";
        Field currentNameField = JsonReadContext.class.getDeclaredField("_currentName");
        currentNameField.setAccessible(true);
        currentNameField.set(context, expectedName);

        // Call the method under test
        String actualName = context.getCurrentName();

        // Verify the result
        assertEquals(expectedName, actualName);
    }
}
