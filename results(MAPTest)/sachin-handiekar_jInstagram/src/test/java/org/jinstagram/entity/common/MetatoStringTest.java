package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetatoStringTest {
    @Test
    public void testToString() throws Exception {
        Meta meta = new Meta();
        // Use reflection to set private fields
        java.lang.reflect.Field codeField = Meta.class.getDeclaredField("code");
        codeField.setAccessible(true);
        codeField.set(meta, 200);

        java.lang.reflect.Field errorMessageField = Meta.class.getDeclaredField("errorMessage");
        errorMessageField.setAccessible(true);
        errorMessageField.set(meta, "No error");

        java.lang.reflect.Field errorTypeField = Meta.class.getDeclaredField("errorType");
        errorTypeField.setAccessible(true);
        errorTypeField.set(meta, "None");

        String result = meta.toString();
        assertEquals("Meta [code=200, errorMessage=No error, errorType=None]", result);
    }
}
