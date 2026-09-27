package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ObjectCodecwriteValueTest {
    @Test
    public void testWriteValueSignature() throws Exception {
        // Verify that the method exists and has the correct signature
        Method method = ObjectCodec.class.getMethod("writeValue", JsonGenerator.class, Object.class);
        Assert.assertEquals(void.class, method.getReturnType());
        Assert.assertTrue(Modifier.isAbstract(method.getModifiers()));
        Assert.assertEquals(2, method.getParameterCount());
        Assert.assertArrayEquals(new Class<?>[]{JsonGenerator.class, Object.class}, method.getParameterTypes());
        Assert.assertTrue(method.getExceptionTypes()[0].equals(IOException.class));
    }
}
