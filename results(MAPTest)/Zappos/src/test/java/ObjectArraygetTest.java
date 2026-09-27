package com.zappos.json.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;

public class ObjectArraygetTest {
    private ObjectArray objectArray;
    private Field elementDataField;
    private Field sizeField;

    @Before
    public void setUp() throws Exception {
        objectArray = new ObjectArray();
        elementDataField = ObjectArray.class.getDeclaredField("elementData");
        elementDataField.setAccessible(true);
        sizeField = ObjectArray.class.getDeclaredField("size");
        sizeField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        objectArray = null;
        elementDataField = null;
        sizeField = null;
    }

    @Test
    public void testGetValidIndex() throws Exception {
        // Arrange
        Object[] testData = {"a", "b", "c"};
        elementDataField.set(objectArray, testData);
        sizeField.set(objectArray, 3);

        // Act
        Object result = objectArray.get(1);

        // Assert
        Assert.assertEquals("b", result);
    }

    @Test
    public void testGetInvalidIndex() throws Exception {
        // Arrange
        Object[] testData = {"a", "b", "c"};
        elementDataField.set(objectArray, testData);
        sizeField.set(objectArray, 3);

        // Act & Assert
        try {
            objectArray.get(3);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(e.getMessage().contains("Index: 3, Size: 3"));
        }
    }
}
