package com.zappos.json.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;

public class ObjectArraytoArray_91fc4400Test {
    private ObjectArray objectArray;
    private Object[] elementData;
    private int size;

    @Before
    public void setUp() throws Exception {
        objectArray = new ObjectArray();
        elementData = new Object[]{1, 2, 3};
        size = 3;

        Field elementDataField = ObjectArray.class.getDeclaredField("elementData");
        elementDataField.setAccessible(true);
        elementDataField.set(objectArray, elementData);

        Field sizeField = ObjectArray.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(objectArray, size);
    }

    @Test
    public void testToArray_ReturnsCopyOfElementDataWithSizeElements() throws Exception {
        Object[] result = objectArray.toArray();

        Assert.assertNotNull(result);
        Assert.assertEquals(size, result.length);
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(elementData[i], result[i]);
        }
        Assert.assertNotSame(elementData, result);
    }
}
