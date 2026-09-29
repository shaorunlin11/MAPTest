package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import com.fasterxml.jackson.core.io.DataOutputAsStream;

import java.net.URL;

import java.lang.reflect.Method;
import java.io.Reader;
import java.io.Writer;

public class TokenStreamFactorycreateParser_5cf8edb9Test {
    @Test
    public void testCreateParserWithByteArray() throws Exception {
        // Verify that the method exists and has the correct signature
        Method method = TokenStreamFactory.class.getMethod("createParser", byte[].class);
        Assert.assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        Assert.assertTrue(method.getReturnType() == JsonParser.class);
        Assert.assertTrue(java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
        Assert.assertTrue(method.getExceptionTypes().length == 1);
        Assert.assertTrue(method.getExceptionTypes()[0] == IOException.class);
    }
}
