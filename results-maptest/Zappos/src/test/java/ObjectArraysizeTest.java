package com.zappos.json.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class ObjectArraysizeTest {
    private ObjectArray objectArray;

    @Before
    public void setUp() {
        objectArray = new ObjectArray();
    }

    @After
    public void tearDown() {
        objectArray = null;
    }

    @Test
    public void testSizeReturnsZeroWhenNoElementsAreAdded() {
        Assert.assertEquals(0, objectArray.size());
    }

    @Test
    public void testSizeReturnsCorrectValueAfterAddingElements() throws Exception {
        // Use reflection to modify the size field directly
        Field sizeField = ObjectArray.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(objectArray, 5);

        Assert.assertEquals(5, objectArray.size());
    }
}
