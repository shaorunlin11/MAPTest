package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionBuilderhasArgs_2e682e94Test {

    @Test
    public void testHasArgsSetsNumberOfArgs() throws Exception {
        // Arrange
        int expectedNum = 3;

        // Act
        OptionBuilder.hasArgs(expectedNum);

        // Assert
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        int actualNum = (int) numberOfArgsField.get(null);
        assertEquals(expectedNum, actualNum);
    }

    @Test
    public void testHasArgsReturnsSingletonInstance() throws Exception {
        // Arrange
        int num = 2;

        // Act
        OptionBuilder result = OptionBuilder.hasArgs(num);

        // Assert
        Field instanceField = OptionBuilder.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        OptionBuilder expectedInstance = (OptionBuilder) instanceField.get(null);
        assertSame(expectedInstance, result);
    }

    @Test
    public void testHasArgsCanBeChained() throws Exception {
        // Arrange
        int num1 = 1;
        int num2 = 2;

        // Act
        OptionBuilder result = OptionBuilder.hasArgs(num1).hasArgs(num2);

        // Assert
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        int actualNum = (int) numberOfArgsField.get(null);
        assertEquals(num2, actualNum);
    }
}
