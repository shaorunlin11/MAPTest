package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionBuilderisRequired_3df10a57Test {

    @Test
    public void testIsRequiredSetsRequiredToTrue() throws Exception {
        // Act
        OptionBuilder result = OptionBuilder.isRequired();

        // Assert
        Field requiredField = OptionBuilder.class.getDeclaredField("required");
        requiredField.setAccessible(true);
        assertTrue((boolean) requiredField.get(null));
    }

    @Test
    public void testIsRequiredReturnsSingletonInstance() throws Exception {
        // Act
        OptionBuilder result = OptionBuilder.isRequired();

        // Assert
        Field instanceField = OptionBuilder.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        Object expectedInstance = instanceField.get(null);
        assertEquals(expectedInstance, result);
    }
}
