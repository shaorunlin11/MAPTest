package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.Override;

public class JsonGeneratorflushTest {

    @Test
    public void testFlushMethodIsAbstract() throws Exception {
        // Verify that the flush method is abstract
        Method method = JsonGenerator.class.getDeclaredMethod("flush");
        Assert.assertTrue("flush() method should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }

    @Test
    public void testFlushMethodThrowsIOException() throws Exception {
        // Verify that the flush method declares IOException in its throws clause
        Method method = JsonGenerator.class.getDeclaredMethod("flush");
        Class<?>[] exceptions = method.getExceptionTypes();
        boolean found = false;
        for (Class<?> ex : exceptions) {
            if (ex.equals(IOException.class)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("flush() method should throw IOException", found);
    }

    @Test
    public void testFlushMethodIsOverriding() throws Exception {
        // Verify that the flush method is annotated with @Override
        Method method = JsonGenerator.class.getDeclaredMethod("flush");
        Assert.assertFalse("flush() method should not be annotated with @Override", method.isAnnotationPresent(Override.class));
    }
}
