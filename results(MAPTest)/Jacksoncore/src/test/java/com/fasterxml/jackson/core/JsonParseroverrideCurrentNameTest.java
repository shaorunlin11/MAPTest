package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Method;

public class JsonParseroverrideCurrentNameTest {
    @Test
    public void testOverrideCurrentNameMethodExists() throws Exception {
        Class<?> clazz = JsonParser.class;
        Method method = clazz.getMethod("overrideCurrentName", String.class);
        Assert.assertTrue("Method overrideCurrentName should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }
}
