package com.zappos.json.format;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.zappos.json.ZapposJson;

public class ValueFormattersetPatternTest {

    @Test
    public void testSetPatternMethodExists() throws Exception {
        Class<?> clazz = Class.forName("com.zappos.json.format.ValueFormatter");
        Method method = clazz.getMethod("setPattern", String.class);
        Assert.assertNotNull(method);
    }

    @Test
    public void testSetPatternReturnsValueFormatter() throws Exception {
        Class<?> clazz = Class.forName("com.zappos.json.format.ValueFormatter");
        Method method = clazz.getMethod("setPattern", String.class);
        Assert.assertEquals(clazz, method.getReturnType());
    }
}
