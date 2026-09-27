package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MetagetCodeTest {

    @Test
    public void testGetCode() throws Exception {
        // Create an instance of Meta
        Meta meta = new Meta();

        // Set the code field using reflection to bypass private access
        Field codeField = Meta.class.getDeclaredField("code");
        codeField.setAccessible(true);
        codeField.setInt(meta, 200);

        // Call the getCode method
        int result = meta.getCode();

        // Assert the result
        assertEquals(200, result);
    }
}
