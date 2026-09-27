package com.zappos.json.util;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Method;

import java.util.List;


public class ReflectionsgetFirstGenericParameterTypeTest {

    @Test
    public void testGetFirstGenericParameterType() throws Exception {
        // Create a method with generic parameters
        Method method = TestClass.class.getMethod("genericMethod", List.class);

        Class<?> result = Reflections.getFirstGenericParameterType(method);
        Assert.assertEquals(String.class, result);
    }

    @Test
    public void testGetFirstGenericParameterTypeWithNoGenericParameters() throws Exception {
        // Create a method without generic parameters
        Method method = TestClass.class.getMethod("nonGenericMethod", String.class);

        Class<?> result = Reflections.getFirstGenericParameterType(method);
        Assert.assertNull(result);
    }

    @Test
    public void testGetFirstGenericParameterTypeWithNullReturn() throws Exception {
        // Create a method where getGenericParameterTypes returns null
        Method method = TestClass.class.getMethod("nullGenericMethod", Object.class);

        Class<?> result = Reflections.getFirstGenericParameterType(method);
        Assert.assertNull(result);
    }

    @Test
    public void testGetFirstGenericParameterTypeWithEmptyArray() throws Exception {
        // Create a method where getGenericParameterTypes returns an empty array
        Method method = TestClass.class.getMethod("emptyGenericMethod", Object.class);

        Class<?> result = Reflections.getFirstGenericParameterType(method);
        Assert.assertNull(result);
    }

    static class TestClass {
        public void genericMethod(List<String> list) {}
        public void nonGenericMethod(String str) {}
        public void nullGenericMethod(Object obj) {}
        public void emptyGenericMethod(Object obj) {}
    }
}
