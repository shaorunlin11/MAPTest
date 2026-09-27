package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class MetagetErrorTypeTest {
    @Test
    public void testGetErrorType() throws Exception {
        Meta meta = new Meta();
        String expectedErrorType = "test_error_type";
        Field errorTypeField = Meta.class.getDeclaredField("errorType");
        errorTypeField.setAccessible(true);
        errorTypeField.set(meta, expectedErrorType);

        String actualErrorType = meta.getErrorType();
        Assert.assertEquals(expectedErrorType, actualErrorType);
    }
}
