package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class OptionsetType_e12b54deTest {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test", false, "test description");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testSetType_DelegatesToSetTypeClass() throws Exception {
        // Arrange
        Class<?> expectedType = Integer.class;

        // Act
        option.setType(expectedType);

        // Assert
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        Class<?> actualType = (Class<?>) typeField.get(option);
        Assert.assertEquals(expectedType, actualType);
    }

    @Test
    public void testSetType_CastsObjectToClass() throws Exception {
        // Arrange
        Object typeObject = String.class;

        // Act
        option.setType(typeObject);

        // Assert
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        Class<?> actualType = (Class<?>) typeField.get(option);
        Assert.assertEquals(String.class, actualType);
    }
}
