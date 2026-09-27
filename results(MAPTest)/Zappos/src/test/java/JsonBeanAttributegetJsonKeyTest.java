package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JsonBeanAttributegetJsonKeyTest {
    private JsonBeanAttribute jsonBeanAttribute;

    @Before
    public void setUp() {
        jsonBeanAttribute = new JsonBeanAttribute();
    }

    @Test
    public void testGetJsonKey_ReturnsInitializedValue() throws Exception {
        // Arrange
        String expectedJsonKey = "testKey";
        Field jsonKeyField = JsonBeanAttribute.class.getDeclaredField("jsonKey");
        jsonKeyField.setAccessible(true);
        jsonKeyField.set(jsonBeanAttribute, expectedJsonKey);

        // Act
        String result = jsonBeanAttribute.getJsonKey();

        // Assert
        Assert.assertEquals(expectedJsonKey, result);
    }

    @Test
    public void testGetJsonKey_DefaultValueIsSetInConstructor() throws Exception {
        // Arrange
        String attributeKey = "testAttribute";
        Method method = JsonBeanAttribute.class.getDeclaredMethod("getJsonKey");
        Field field = JsonBeanAttribute.class.getDeclaredField("jsonKey");
        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, attributeKey);

        // Act
        String result = attribute.getJsonKey();

        // Assert
        Assert.assertEquals(attributeKey, result);
    }
}
