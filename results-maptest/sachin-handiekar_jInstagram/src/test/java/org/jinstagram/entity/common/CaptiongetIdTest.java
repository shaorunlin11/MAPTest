package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class CaptiongetIdTest {
    @Test
    public void testGetId() throws Exception {
        Caption caption = new Caption();
        String expectedId = "12345";
        Field idField = Caption.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(caption, expectedId);

        String actualId = caption.getId();
        Assert.assertEquals(expectedId, actualId);
    }
}
