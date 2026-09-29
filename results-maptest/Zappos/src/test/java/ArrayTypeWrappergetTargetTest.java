package com.zappos.json.wrapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ArrayTypeWrappergetTargetTest {
    @Test
    public void testGetTarget() throws Exception {
        // Create an instance of ArrayTypeWrapper with a specific type
        Class<?> componentType = String.class;
        ArrayTypeWrapper<String> wrapper = new ArrayTypeWrapper<>();

        // Set the target field using reflection to bypass access modifiers
        Field targetField = ArrayTypeWrapper.class.getDeclaredField("target");
        targetField.setAccessible(true);
        targetField.set(wrapper, "testValue");

        // Call the method and verify the result
        String result = wrapper.getTarget();
        assertEquals("testValue", result);
    }
}
