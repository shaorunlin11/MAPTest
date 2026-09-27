package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;


public class TokenStreamFactorygetFormatNameTest {

    @Test
    public void testGetFormatNameIsAbstract() throws Exception {
        // Since getFormatName is abstract, we cannot instantiate TokenStreamFactory directly
        // We verify that the method is indeed abstract by checking its modifiers
        Method method = TokenStreamFactory.class.getMethod("getFormatName");
        assertTrue("getFormatName should be an abstract method", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }
}
