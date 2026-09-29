package com.fasterxml.jackson.core;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
public class JsonParserversionTest {
    @Test
    public void testVersionMethodExists() throws Exception {
        Class<?> clazz = Class.forName("com.fasterxml.jackson.core.JsonParser");
        Method method = clazz.getMethod("version");
        assertNotNull("version() method should exist", method);
    }
}
