package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
public class ReflectionsgetSecondGenericParameterTypeTest {

    @Test
    public void testGetSecondGenericParameterType_withFewerThanTwoGenericParameters() throws Exception {
        // Create a method with one generic parameter
        Method method = TestClass.class.getMethod("testMethodWithOneParam", java.util.List.class);

        // Get the second generic parameter type
        Class<?> result = Reflections.getSecondGenericParameterType(method);

        // Assert that the result is null
        Assert.assertNull(result);
    }

    @Test
    public void testGetSecondGenericParameterType_withNullGenericParameterTypes() throws Exception {
        // Create a method with no generic parameters
        Method method = TestClass.class.getMethod("testMethodWithNoParams");

        // Get the second generic parameter type
        Class<?> result = Reflections.getSecondGenericParameterType(method);

        // Assert that the result is null
        Assert.assertNull(result);
    }

    // Test class with methods for testing
    static class TestClass {
        public void testMethod(List<String> list, Map<String, String> map) {}
        public void testMethodWithOneParam(List<String> list) {}
        public void testMethodWithNoParams() {}
    }
}
