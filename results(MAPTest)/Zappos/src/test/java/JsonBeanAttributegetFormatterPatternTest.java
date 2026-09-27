package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;

public class JsonBeanAttributegetFormatterPatternTest {
    private JsonBeanAttribute attribute;

    @Before
    public void setUp() {
        attribute = new JsonBeanAttribute();
    }

    @Test
    public void testGetFormatterPattern_returnsNullWhenNotSet() {
        Assert.assertNull(attribute.getFormatterPattern());
    }

    @Test
    public void testGetFormatterPattern_returnsSetStringValue() throws Exception {
        String expectedPattern = "yyyy-MM-dd";
        Field formatterPatternField = JsonBeanAttribute.class.getDeclaredField("formatterPattern");
        formatterPatternField.setAccessible(true);
        formatterPatternField.set(attribute, expectedPattern);
        Assert.assertEquals(expectedPattern, attribute.getFormatterPattern());
    }
}
