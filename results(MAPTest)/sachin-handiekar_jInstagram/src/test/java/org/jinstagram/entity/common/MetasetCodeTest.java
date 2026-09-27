package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

public class MetasetCodeTest {
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
    public void testSetCode() throws Exception {
        int expectedCode = 200;
        meta.setCode(expectedCode);

        Field codeField = Meta.class.getDeclaredField("code");
        codeField.setAccessible(true);
        int actualCode = ((Integer) codeField.get(meta)).intValue();

        Assert.assertEquals(expectedCode, actualCode);
    }
}
