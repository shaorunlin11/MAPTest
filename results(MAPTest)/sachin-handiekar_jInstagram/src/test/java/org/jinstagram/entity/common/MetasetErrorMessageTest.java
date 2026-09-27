package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


public class MetasetErrorMessageTest {
    private Meta meta;

    @Before
    public void setUp() {
        meta = new Meta();
    }

    @After
    public void tearDown() {
        meta = null;
    }

    @Test
    public void testSetErrorMessage() throws Exception {
        String expectedErrorMessage = "Test error message";
        meta.setErrorMessage(expectedErrorMessage);

        Field errorMessageField = Meta.class.getDeclaredField("errorMessage");
        errorMessageField.setAccessible(true);
        String actualErrorMessage = (String) errorMessageField.get(meta);

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testSetErrorMessageWithNull() throws Exception {
        meta.setErrorMessage(null);

        Field errorMessageField = Meta.class.getDeclaredField("errorMessage");
        errorMessageField.setAccessible(true);
        String actualErrorMessage = (String) errorMessageField.get(meta);

        Assert.assertNull(actualErrorMessage);
    }
}
