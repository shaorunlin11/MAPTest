package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;


public class TypeHandlercreateClassTest {

    @Test
    public void testCreateClassSuccessfully() throws Exception {
        Class<?> clazz = TypeHandler.createClass("java.util.ArrayList");
        Assert.assertNotNull(clazz);
        Assert.assertEquals(ArrayList.class, clazz);
    }

    @Test(expected = ParseException.class)
    public void testCreateClassFailsWithInvalidClassName() throws Exception {
        TypeHandler.createClass("invalid.Class.Name");
    }
}
