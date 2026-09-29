package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class CaptiongetCreatedTimeTest {
    private Caption caption;

    @Before
    public void setUp() {
        caption = new Caption();
    }

    @After
    public void tearDown() {
        caption = null;
    }

    @Test
    public void testGetCreatedTime_ReturnsInitializedValue() throws Exception {
        String expected = "2023-04-05T12:34:56Z";
        Field createdTimeField = Caption.class.getDeclaredField("createdTime");
        createdTimeField.setAccessible(true);
        createdTimeField.set(caption, expected);

        String result = caption.getCreatedTime();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetCreatedTime_ReturnsNullWhenNotInitialized() throws Exception {
        Field createdTimeField = Caption.class.getDeclaredField("createdTime");
        createdTimeField.setAccessible(true);
        createdTimeField.set(caption, null);

        String result = caption.getCreatedTime();
        Assert.assertNull(result);
    }
}
