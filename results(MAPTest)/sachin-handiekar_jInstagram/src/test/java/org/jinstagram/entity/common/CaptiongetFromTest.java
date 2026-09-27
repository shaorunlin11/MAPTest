package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class CaptiongetFromTest {
    @Test
    public void testGetFrom() throws Exception {
        // Create a Caption instance
        Caption caption = new Caption();

        // Test 1: Verify that getFrom returns null when 'from' is not initialized
        Assert.assertNull("getFrom should return null when 'from' is not initialized", caption.getFrom());

        // Test 2: Initialize 'from' field and verify getFrom returns the expected value
        FromTagData expectedFrom = new FromTagData();
        Field fromField = Caption.class.getDeclaredField("from");
        fromField.setAccessible(true);
        fromField.set(caption, expectedFrom);

        Assert.assertEquals("getFrom should return the initialized 'from' object", expectedFrom, caption.getFrom());
    }
}
