package com.fasterxml.jackson.core.async;

import org.junit.Test;
import java.io.IOException;

import java.lang.reflect.Method;


public class ByteArrayFeederfeedInputTest {
    @Test
    public void testFeedInputMethodSignature() throws Exception {
        // This test verifies that the method signature exists as declared
        Method method = ByteArrayFeeder.class.getMethod("feedInput", byte[].class, int.class, int.class);
        assert method.getReturnType() == void.class;
        assert method.getExceptionTypes().length == 1;
        assert method.getExceptionTypes()[0] == IOException.class;
    }
}
