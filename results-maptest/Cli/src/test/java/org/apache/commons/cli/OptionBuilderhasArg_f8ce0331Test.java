package org.apache.commons.cli;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class OptionBuilderhasArg_f8ce0331Test {

    @Test
    public void testHasArg() throws Exception {
        // Call the method
        OptionBuilder result = OptionBuilder.hasArg();

        // Verify that the returned instance is the singleton
        Field instanceField = OptionBuilder.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        Object expectedInstance = instanceField.get(null);
        assertTrue("Expected the same INSTANCE to be returned", result == expectedInstance);

        // Verify that numberOfArgs was set to 1
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        int actualNumberOfArgs = numberOfArgsField.getInt(null);
        assertEquals("numberOfArgs should be set to 1", 1, actualNumberOfArgs);
    }
}
