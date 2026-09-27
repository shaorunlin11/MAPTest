package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class MetagetErrorMessageTest {

    @Test
    public void testGetErrorMessage() {
        Meta meta = new Meta();
        String expectedErrorMessage = "Test error message";
        // Use reflection to set private field
        try {
            java.lang.reflect.Field field = Meta.class.getDeclaredField("errorMessage");
            field.setAccessible(true);
            field.set(meta, expectedErrorMessage);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }

        String actualErrorMessage = meta.getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testGetErrorMessageWithNull() {
        Meta meta = new Meta();
        String actualErrorMessage = meta.getErrorMessage();
        Assert.assertNull(actualErrorMessage);
    }
}
