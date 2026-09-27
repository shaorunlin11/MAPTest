package com.zappos.json.wrapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ArrayTypeWrappergetComponentTypeTest {
    @Test
    public void testGetComponentType() throws Exception {
        Class<?> expectedComponentType = String.class;
        ArrayTypeWrapper<String> wrapper = new ArrayTypeWrapper<>();

        // Use reflection to set the private componentType field
        Field componentTypeField = ArrayTypeWrapper.class.getDeclaredField("componentType");
        componentTypeField.setAccessible(true);
        componentTypeField.set(wrapper, expectedComponentType);

        Class<?> result = wrapper.getComponentType();
        assertEquals(expectedComponentType, result);
    }
}
