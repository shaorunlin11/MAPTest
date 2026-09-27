package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MediaFeedDatasetCreatedTimeTest {
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
    }

    @Test
    public void testSetCreatedTime() throws Exception {
        String expectedCreatedTime = "1625145600";
        mediaFeedData.setCreatedTime(expectedCreatedTime);

        Field createdTimeField = MediaFeedData.class.getDeclaredField("createdTime");
        createdTimeField.setAccessible(true);
        String actualCreatedTime = (String) createdTimeField.get(mediaFeedData);

        assertEquals(expectedCreatedTime, actualCreatedTime);
    }
}
