package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.util.List;
public class ReflectionsgetFirstGenericTypeTest {

    @Test
    public void testGetFirstGenericType_returnsFirstTypeWhenGenericTypesIsNonEmpty() throws Exception {
        Field field = ReflectionsgetFirstGenericTypeTest.class.getDeclaredField("testField");
        Class<?> result = Reflections.getFirstGenericType(field);
        Assert.assertEquals(String.class, result);
    }

    @Test
    public void testGetFirstGenericType_returnsNullWhenTypesIsNullAndLengthIsZero() throws Exception {
        Field field = ReflectionsgetFirstGenericTypeTest.class.getDeclaredField("emptyTestField");
        Class<?> result = Reflections.getFirstGenericType(field);
        Assert.assertNull(result);
    }

    // Dummy fields for testing purposes
    private List<String> testField;
    private List<?> emptyTestField;
}
