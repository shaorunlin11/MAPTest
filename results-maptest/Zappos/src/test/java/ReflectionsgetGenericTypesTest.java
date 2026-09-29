package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ReflectionsgetGenericTypesTest {
    @Test
    public void testGetGenericTypesWithParameterizedType() throws Exception {
        // Create a field with a parameterized type (List<String>)
        Field field = TestClass.class.getDeclaredField("listField");
        Class<?>[] genericTypes = Reflections.getGenericTypes(field);
        Assert.assertNotNull(genericTypes);
        Assert.assertEquals(1, genericTypes.length);
        Assert.assertEquals(String.class, genericTypes[0]);
    }

    @Test
    public void testGetGenericTypesWithMultipleTypeArguments() throws Exception {
        // Create a field with a parameterized type (Map<String, Integer>)
        Field field = TestClass.class.getDeclaredField("mapField");
        Class<?>[] genericTypes = Reflections.getGenericTypes(field);
        Assert.assertNotNull(genericTypes);
        Assert.assertEquals(2, genericTypes.length);
        Assert.assertEquals(String.class, genericTypes[0]);
        Assert.assertEquals(Integer.class, genericTypes[1]);
    }

    @Test
    public void testGetGenericTypesWithNonParameterizedType() throws Exception {
        // Create a field with a non-parameterized type (String)
        Field field = TestClass.class.getDeclaredField("stringField");
        Class<?>[] genericTypes = Reflections.getGenericTypes(field);
        Assert.assertNull(genericTypes);
    }


    @Test
    public void testGetGenericTypesWithNoTypeArguments() throws Exception {
        // Create a field with a parameterized type but no type arguments (List)
        Field field = TestClass.class.getDeclaredField("rawListField");
        Class<?>[] genericTypes = Reflections.getGenericTypes(field);
        Assert.assertNull(genericTypes);
    }

    private static class TestClass {
        List<String> listField;
        Map<String, Integer> mapField;
        String stringField;
        List<? extends Number> wildcardField;
        List rawListField;
    }
}
