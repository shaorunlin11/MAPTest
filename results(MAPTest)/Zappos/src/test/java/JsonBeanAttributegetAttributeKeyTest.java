package com.zappos.json;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.zappos.json.annot.JsonEnum.EnumValue;
import com.zappos.json.format.ValueFormatter;

import static org.junit.Assert.assertEquals;

public class JsonBeanAttributegetAttributeKeyTest {
    @Test
    public void testGetAttributeKey() throws Exception {
        // Arrange
        Method method = String.class.getMethod("toString");
        Field field = String.class.getDeclaredField("value");
        String attributeKey = "testKey";

        JsonBeanAttribute jsonBeanAttribute = new JsonBeanAttribute(method, field, attributeKey);

        // Act
        String result = jsonBeanAttribute.getAttributeKey();

        // Assert
        assertEquals(attributeKey, result);
    }
}
