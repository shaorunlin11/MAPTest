package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class ReflectionsgetGenericParameterTypesTest {
    @Test
    public void testGetGenericParameterTypesWithParameterizedType() throws Exception {
        Method method = TestClass.class.getMethod("methodWithGenericParams", List.class);
        Class<?>[] result = Reflections.getGenericParameterTypes(method);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(String.class, result[0]);
    }

    @Test
    public void testGetGenericParameterTypesWithRawType() throws Exception {
        Method method = TestClass.class.getMethod("methodWithRawParam", List.class);
        Class<?>[] result = Reflections.getGenericParameterTypes(method);
        Assert.assertNull(result);
    }


    @Test
    public void testGetGenericParameterTypesWithNoGenericTypes() throws Exception {
        Method method = TestClass.class.getMethod("methodWithoutGenericParams", String.class);
        Class<?>[] result = Reflections.getGenericParameterTypes(method);
        Assert.assertNull(result);
    }

    static class TestClass {
        public void methodWithGenericParams(List<String> param) {}
        public void methodWithRawParam(List param) {}
        public void methodWithWildcardParam(List<?> param) {}
        public void methodWithoutGenericParams(String param) {}
    }
}
