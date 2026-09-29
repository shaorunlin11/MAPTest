package com.fasterxml.jackson.core.io;

import java.io.*;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Method;

public class InputDecoratordecorate_358e4ed9Test {
    @Test
    public void testDecorateMethodSignature() throws Exception {
        // Create a mock subclass of InputDecorator to test the abstract method
        Class<?> clazz = Class.forName("com.fasterxml.jackson.core.io.InputDecorator");

        // Use a concrete subclass for testing
        Class<?> subClass = Class.forName("com.fasterxml.jackson.core.io.InputDecorator");

        Method method = subClass.getDeclaredMethod("decorate", IOContext.class, Reader.class);

        Assert.assertEquals("java.io.Reader", method.getReturnType().getName());
        Assert.assertEquals(2, method.getParameterCount());
        Assert.assertTrue(method.getParameterTypes()[0].equals(IOContext.class));
        Assert.assertTrue(method.getParameterTypes()[1].equals(Reader.class));
        Assert.assertTrue(method.getExceptionTypes()[0].equals(IOException.class));
    }
}
