package com.zappos.json;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;

public class JsonBeanAttributesetFormatterPatternTest {
    @Test
    public void testSetFormatterPattern() throws Exception {
        // Arrange
        Method method = Method.class.getDeclaredMethod("getModifiers", new Class[0]);
        Field field = Field.class.getDeclaredField("name");
        String attributeKey = "testAttribute";
        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, attributeKey);

        // Act
        String pattern = "yyyy-MM-dd";
        JsonBeanAttribute result = attribute.setFormatterPattern(pattern);

        // Assert
        assertEquals(pattern, attribute.getFormatterPattern());
        assertSame(attribute, result);
    }

    @Test
    public void testSetFormatterPatternWithNull() throws Exception {
        // Arrange
        Method method = Method.class.getDeclaredMethod("getModifiers", new Class[0]);
        Field field = Field.class.getDeclaredField("name");
        String attributeKey = "testAttribute";
        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, attributeKey);

        // Act
        JsonBeanAttribute result = attribute.setFormatterPattern(null);

        // Assert
        assertNull(attribute.getFormatterPattern());
        assertSame(attribute, result);
    }

    // Helper method to access private field
    private String getFormatterPattern(JsonBeanAttribute attribute) throws Exception {
        Field field = JsonBeanAttribute.class.getDeclaredField("formatterPattern");
        field.setAccessible(true);
        return (String) field.get(attribute);
    }
}
