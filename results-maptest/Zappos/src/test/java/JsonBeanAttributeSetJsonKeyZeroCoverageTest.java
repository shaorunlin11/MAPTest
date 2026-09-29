package com.zappos.json;

import org.junit.Test;

import com.zappos.json.format.JavaSqlDateFormatter;


public class JsonBeanAttributeSetJsonKeyZeroCoverageTest {
    @Test
    public void testSetJsonKey() throws Exception {
        JsonBeanAttribute attribute = new JsonBeanAttribute();
        String jsonKey = "testKey";
        attribute.setJsonKey(jsonKey);
        assert attribute.getJsonKey().equals(jsonKey);
    }
}
