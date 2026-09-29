package org.apache.commons.cli;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class OptionBuilderwithDescriptionTest {

    @Test
    public void testWithDescriptionSetsDescriptionField() throws Exception {
        // Arrange
        String expectedDescription = "Test description";

        // Act
        OptionBuilder.withDescription(expectedDescription);

        // Assert
        Field descriptionField = OptionBuilder.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        String actualDescription = (String) descriptionField.get(null);
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testWithDescriptionReturnsSameInstance() {
        // Arrange
        String description = "Test description";

        // Act
        OptionBuilder result1 = OptionBuilder.withDescription(description);
        OptionBuilder result2 = OptionBuilder.withDescription(description);

        // Assert
        assertSame(result1, result2);
    }
}
