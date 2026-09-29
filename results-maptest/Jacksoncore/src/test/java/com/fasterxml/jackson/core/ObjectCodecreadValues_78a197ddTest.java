package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.ResolvedType;
import java.io.IOException;
import java.util.Iterator;

import java.lang.reflect.Method;

public class ObjectCodecreadValues_78a197ddTest {

    @Test
    public void testReadValuesMethodSignature() throws Exception {
        // This test verifies that the method signature exists as expected
        // and can be accessed via reflection
        Class<?> clazz = ObjectCodec.class;
        Method method = clazz.getMethod("readValues", JsonParser.class, ResolvedType.class);

        Assert.assertNotNull("Method readValues should exist", method);
        Assert.assertEquals("Return type of readValues should be Iterator<T>", Iterator.class, method.getReturnType());
        Assert.assertTrue("Method should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }
}
