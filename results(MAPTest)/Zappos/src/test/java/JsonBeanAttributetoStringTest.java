package com.zappos.json;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class JsonBeanAttributetoStringTest {

    @Test
    public void testToStringWithField() throws Exception {
        Field field = String.class.getDeclaredField("value");
        Method method = String.class.getMethod("toString");

        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, "attributeKey");
        assertEquals("value", attribute.toString());
    }

    @Test
    public void testToStringWithMethod() throws Exception {
        Method method = String.class.getMethod("toString");
        Field field = null;

        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, "attributeKey");
        assertEquals("toString", attribute.toString());
    }
}
