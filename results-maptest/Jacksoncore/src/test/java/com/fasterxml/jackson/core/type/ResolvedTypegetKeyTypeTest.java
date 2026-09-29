package com.fasterxml.jackson.core.type;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;


public class ResolvedTypegetKeyTypeTest {
    @Test
    public void testGetKeyTypeIsAbstract() throws Exception {
        Class<?> clazz = ResolvedType.class;
        Method method = clazz.getMethod("getKeyType");
        assertTrue("getKeyType should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }
}
