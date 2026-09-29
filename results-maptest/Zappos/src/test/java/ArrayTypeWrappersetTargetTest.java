package com.zappos.json.wrapper;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ArrayTypeWrappersetTargetTest {
    private ArrayTypeWrapper<String> wrapper;

    @Before
    public void setUp() {
        wrapper = new ArrayTypeWrapper<>();
    }

    @Test
    public void testSetTargetAssignsValueToTargetField() throws Exception {
        String testValue = "test";
        wrapper.setTarget(testValue);

        Field targetField = ArrayTypeWrapper.class.getDeclaredField("target");
        targetField.setAccessible(true);
        Object result = targetField.get(wrapper);

        assertEquals(testValue, result);
    }
}
