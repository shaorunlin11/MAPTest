package com.zappos.json.format;

import org.junit.Test;
import org.junit.Assert;
import com.zappos.json.ZapposJson;

import java.lang.reflect.Method;


public class ValueFormatterparseTest {
    @Test
    public void testParseMethodSignature() throws Exception {
        // This test verifies that the method signature exists as expected
        // and can be accessed via reflection, which is necessary for testing.
        Method method = ValueFormatter.class.getMethod("parse", ZapposJson.class, String.class);
        Assert.assertNotNull(method);
    }
}
