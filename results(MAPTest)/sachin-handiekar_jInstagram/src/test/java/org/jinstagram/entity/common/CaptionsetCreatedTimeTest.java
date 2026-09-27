package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class CaptionsetCreatedTimeTest {
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
    public void testSetCreatedTime() throws Exception {
        String expectedCreatedTime = "1234567890";
        caption.setCreatedTime(expectedCreatedTime);

        java.lang.reflect.Field field = Caption.class.getDeclaredField("createdTime");
        field.setAccessible(true);
        String actualCreatedTime = (String) field.get(caption);

        Assert.assertEquals(expectedCreatedTime, actualCreatedTime);
    }
}
