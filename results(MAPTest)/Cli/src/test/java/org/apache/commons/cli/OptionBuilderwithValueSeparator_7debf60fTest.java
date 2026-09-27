package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionBuilderwithValueSeparator_7debf60fTest {

    @Test
    public void testWithValueSeparator() throws Exception {
        // Arrange
        char expectedSep = ':';

        // Act
        OptionBuilder.withValueSeparator(expectedSep);

        // Assert
        Field valuesepField = OptionBuilder.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        char actualSep = valuesepField.getChar(OptionBuilder.class);
        assertEquals(expectedSep, actualSep);
    }
}
