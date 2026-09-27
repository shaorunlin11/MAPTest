package com.fasterxml.jackson.core.base;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.io.IOException;

import java.lang.reflect.Method;


public class ParserMinimalBasecloseTest {

    @Test
    public void testCloseMethodIsAbstract() throws Exception {
        // This test verifies that the close method is abstract and must be implemented by subclasses
        Method method = ParserMinimalBase.class.getDeclaredMethod("close");
        Assert.assertTrue("close() method should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }

    @Test
    public void testCloseMethodThrowsIOException() throws Exception {
        // This test verifies that the close method throws IOException
        Method method = ParserMinimalBase.class.getDeclaredMethod("close");
        Class<?>[] exceptions = method.getExceptionTypes();
        Assert.assertTrue("close() method should throw IOException", containsException(exceptions, IOException.class));
    }

    private boolean containsException(Class<?>[] exceptions, Class<?> exceptionClass) {
        for (Class<?> ex : exceptions) {
            if (ex.equals(exceptionClass)) {
                return true;
            }
        }
        return false;
    }
}
