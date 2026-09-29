package org.apache.commons.cli;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class OptionBuilderwithValueSeparator_922f7684Test {

    @Test
    public void testWithValueSeparatorSetsValuesepToEquals() throws Exception {
        // Act
        OptionBuilder.withValueSeparator();

        // Assert
        Field valuesepField = OptionBuilder.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        char actualValuesep = (char) valuesepField.get(null);
        assertEquals('=', actualValuesep);
    }

    @Test
    public void testWithValueSeparatorReturnsSingletonInstance() throws Exception {
        // Act
        OptionBuilder result = OptionBuilder.withValueSeparator();

        // Assert
        Field instanceField = OptionBuilder.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        OptionBuilder expectedInstance = (OptionBuilder) instanceField.get(null);
        assertSame(expectedInstance, result);
    }
}
