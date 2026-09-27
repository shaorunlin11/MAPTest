package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionBuilderhasOptionalArgs_b7788142Test {

    @Test
    public void testHasOptionalArgs() throws Exception {
        // Arrange
        int numArgs = 2;

        // Act
        OptionBuilder result = OptionBuilder.hasOptionalArgs(numArgs);

        // Assert
        assertNotNull("The returned instance should not be null", result);

        // Use reflection to access private static fields
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        assertEquals("numberOfArgs should be set to the provided value", numArgs, numberOfArgsField.get(null));

        Field optionalArgField = OptionBuilder.class.getDeclaredField("optionalArg");
        optionalArgField.setAccessible(true);
        assertTrue("optionalArg should be set to true", (Boolean) optionalArgField.get(null));
    }
}
