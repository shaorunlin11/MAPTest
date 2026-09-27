package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;


public class OptionBuildercreate_a94340fcTest {
    @Before
    public void setUp() {
        // Reset static fields before each test
        // Using reflection to reset private static fields
        try {
            Field longoptField = OptionBuilder.class.getDeclaredField("longopt");
            longoptField.setAccessible(true);
            longoptField.set(null, null);

            Field descriptionField = OptionBuilder.class.getDeclaredField("description");
            descriptionField.setAccessible(true);
            descriptionField.set(null, null);

            Field argNameField = OptionBuilder.class.getDeclaredField("argName");
            argNameField.setAccessible(true);
            argNameField.set(null, null);

            Field requiredField = OptionBuilder.class.getDeclaredField("required");
            requiredField.setAccessible(true);
            requiredField.set(null, false);

            Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
            numberOfArgsField.setAccessible(true);
            numberOfArgsField.set(null, Option.UNINITIALIZED);

            Field typeField = OptionBuilder.class.getDeclaredField("type");
            typeField.setAccessible(true);
            typeField.set(null, null);

            Field optionalArgField = OptionBuilder.class.getDeclaredField("optionalArg");
            optionalArgField.setAccessible(true);
            optionalArgField.set(null, false);

            Field valuesepField = OptionBuilder.class.getDeclaredField("valuesep");
            valuesepField.setAccessible(true);
            valuesepField.set(null, '\0');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        // Reset static fields after each test
        // Using reflection to reset private static fields
        try {
            Field longoptField = OptionBuilder.class.getDeclaredField("longopt");
            longoptField.setAccessible(true);
            longoptField.set(null, null);

            Field descriptionField = OptionBuilder.class.getDeclaredField("description");
            descriptionField.setAccessible(true);
            descriptionField.set(null, null);

            Field argNameField = OptionBuilder.class.getDeclaredField("argName");
            argNameField.setAccessible(true);
            argNameField.set(null, null);

            Field requiredField = OptionBuilder.class.getDeclaredField("required");
            requiredField.setAccessible(true);
            requiredField.set(null, false);

            Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
            numberOfArgsField.setAccessible(true);
            numberOfArgsField.set(null, Option.UNINITIALIZED);

            Field typeField = OptionBuilder.class.getDeclaredField("type");
            typeField.setAccessible(true);
            typeField.set(null, null);

            Field optionalArgField = OptionBuilder.class.getDeclaredField("optionalArg");
            optionalArgField.setAccessible(true);
            optionalArgField.set(null, false);

            Field valuesepField = OptionBuilder.class.getDeclaredField("valuesep");
            valuesepField.setAccessible(true);
            valuesepField.set(null, '\0');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_ThrowsIllegalArgumentExceptionWhenLongoptIsNull() throws IllegalArgumentException {
        // Arrange: longopt is null by default
        // Act
        OptionBuilder.create();
    }

    @Test
    public void testCreate_DelegatesToCreateNullWhenLongoptIsSet() throws IllegalArgumentException {
        // Arrange
        try {
            Field longoptField = OptionBuilder.class.getDeclaredField("longopt");
            longoptField.setAccessible(true);
            longoptField.set(null, "test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Act
        Option result = OptionBuilder.create();
        // Assert
        Assert.assertNotNull(result);
    }
}
