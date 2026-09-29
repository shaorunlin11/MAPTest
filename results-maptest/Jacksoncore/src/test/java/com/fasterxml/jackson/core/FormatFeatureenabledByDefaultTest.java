package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;


public class FormatFeatureenabledByDefaultTest {
    @Test
    public void testEnabledByDefault() throws Exception {
        // Since FormatFeature is an interface and this method is declared but not implemented,
        // we cannot directly test it without an implementation.
        // However, we can verify that the method exists and has the correct signature.

        Method method = FormatFeature.class.getMethod("enabledByDefault");
        assertNotNull("Method enabledByDefault should exist in FormatFeature interface", method);
        assertEquals("Return type of enabledByDefault should be boolean", boolean.class, method.getReturnType());
    }
}
