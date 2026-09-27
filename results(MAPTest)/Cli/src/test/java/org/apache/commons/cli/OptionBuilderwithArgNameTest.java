package org.apache.commons.cli;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OptionBuilderwithArgNameTest {

    @Test
    public void testWithArgNameSetsArgNameAndReturnsInstance() throws Exception {
        String expectedArgName = "testArg";

        // Call the method
        OptionBuilder result = OptionBuilder.withArgName(expectedArgName);

        // Verify that the returned object is not null
        assertNotNull(result);

        // Use reflection to access the private static argName field
        Field argNameField = OptionBuilder.class.getDeclaredField("argName");
        argNameField.setAccessible(true);
        String actualArgName = (String) argNameField.get(null);

        // Verify that the argName was set correctly
        assertEquals(expectedArgName, actualArgName);
    }
}
