package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;

public class MetasetErrorTypeTest {
    private Meta meta;
    private Field errorTypeField;

    @Before
    public void setUp() throws Exception {
        meta = new Meta();
        errorTypeField = Meta.class.getDeclaredField("errorType");
        errorTypeField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        meta = null;
        errorTypeField = null;
    }

    @Test
    public void testSetErrorTypeWithNonNullValue() throws Exception {
        String testValue = "testErrorType";
        meta.setErrorType(testValue);
        Assert.assertEquals(testValue, errorTypeField.get(meta));
    }

    @Test
    public void testSetErrorTypeWithNullValue() throws Exception {
        meta.setErrorType(null);
        Assert.assertNull(errorTypeField.get(meta));
    }
}
