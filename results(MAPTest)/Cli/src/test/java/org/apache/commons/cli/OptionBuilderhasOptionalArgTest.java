package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptionBuilderhasOptionalArgTest {

    @Test
    public void testHasOptionalArgSetsNumberOfArgsTo1AndOptionalArgToTrue() throws Exception {
        // Act
        OptionBuilder result = OptionBuilder.hasOptionalArg();

        // Use reflection to access private fields
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        int numberOfArgs = (int) numberOfArgsField.get(null);

        Field optionalArgField = OptionBuilder.class.getDeclaredField("optionalArg");
        optionalArgField.setAccessible(true);
        boolean optionalArg = (boolean) optionalArgField.get(null);

        Field instanceField = OptionBuilder.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        OptionBuilder instance = (OptionBuilder) instanceField.get(null);

        // Assert
        assertEquals(1, numberOfArgs);
        assertTrue(optionalArg);
        assertSame(instance, result);
    }
}
