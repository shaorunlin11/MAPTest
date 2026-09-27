package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
public class ReflectionsgetSecondGenericTypeTest {
    @Test
    public void testGetSecondGenericType_returnsNullWhenNoSecondType() throws Exception {
        Field field = ReflectionsgetSecondGenericTypeTest.class.getDeclaredField("testFieldWithOneType");
        Class<?> result = Reflections.getSecondGenericType(field);
        Assert.assertNull(result);
    }

    @Test
    public void testGetSecondGenericType_returnsNullWhenGenericTypesIsNull() throws Exception {
        Field field = ReflectionsgetSecondGenericTypeTest.class.getDeclaredField("testFieldWithNullGenericTypes");
        Class<?> result = Reflections.getSecondGenericType(field);
        Assert.assertNull(result);
    }

    // Test fields with different generic type configurations
    private final List<String> testFieldWithTwoTypes = new ArrayList<>();
    private final List<String> testFieldWithOneType = new ArrayList<>();
    private final List<String> testFieldWithNullGenericTypes = new ArrayList<>();
}
