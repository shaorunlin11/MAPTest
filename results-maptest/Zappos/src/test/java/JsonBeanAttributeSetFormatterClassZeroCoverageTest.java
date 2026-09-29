package com.zappos.json;

import org.junit.Test;

import com.zappos.json.format.ValueFormatter;
import com.zappos.json.format.NoOpValueFormatter;


public class JsonBeanAttributeSetFormatterClassZeroCoverageTest {
    @Test
    public void testSetFormatterClassWithNonNullValue() {
        JsonBeanAttribute attribute = new JsonBeanAttribute();
        Class<? extends ValueFormatter<?>> formatterClass = com.zappos.json.format.NoOpValueFormatter.class;
        attribute.setFormatterClass(formatterClass);
    }
}
