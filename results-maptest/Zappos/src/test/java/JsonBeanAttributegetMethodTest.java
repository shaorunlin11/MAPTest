package com.zappos.json;

import org.junit.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JsonBeanAttributegetMethodTest {

    @Test
    public void testGetMethod_ReturnsInitializedMethod() throws Exception {
        // Arrange
        Method testMethod = JsonBeanAttribute.class.getDeclaredMethod("getMethod");
        Field testField = JsonBeanAttribute.class.getDeclaredField("method");
        String testAttributeKey = "testKey";

        JsonBeanAttribute attribute = new JsonBeanAttribute(testMethod, testField, testAttributeKey);

        // Act
        Method result = attribute.getMethod();

        // Assert
        assertEquals(testMethod, result);
    }

    @Test
    public void testGetMethod_ReturnsNullWhenNotInitialized() {
        // Arrange
        JsonBeanAttribute attribute = new JsonBeanAttribute();

        // Act
        Method result = attribute.getMethod();

        // Assert
        assertNull(result);
    }
}
