package com.zappos.json.wrapper;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.lang.reflect.Field;

public class ArrayTypeWrappersetComponentTypeTest {
    private ArrayTypeWrapper<?> wrapper;
    private Field componentTypeField;

    @Before
    public void setUp() throws Exception {
        wrapper = new ArrayTypeWrapper<>();
        componentTypeField = ArrayTypeWrapper.class.getDeclaredField("componentType");
        componentTypeField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        componentTypeField.setAccessible(false);
    }

    @Test
    public void testSetComponentType() throws Exception {
        Class<?> expected = String.class;
        wrapper.setComponentType(expected);
        Class<?> actual = (Class<?>) componentTypeField.get(wrapper);
        assert actual == expected;
    }

    @Test
    public void testSetComponentTypeWithNull() throws Exception {
        wrapper.setComponentType(null);
        Class<?> actual = (Class<?>) componentTypeField.get(wrapper);
        assert actual == null;
    }
}
