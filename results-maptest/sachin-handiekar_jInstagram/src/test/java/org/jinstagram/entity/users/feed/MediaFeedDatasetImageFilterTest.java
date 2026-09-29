package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class MediaFeedDatasetImageFilterTest {
    private MediaFeedData mediaFeedData;
    private Field imageFilterField;

    @Before
    public void setUp() throws Exception {
        mediaFeedData = new MediaFeedData();
        imageFilterField = MediaFeedData.class.getDeclaredField("imageFilter");
        imageFilterField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        mediaFeedData = null;
        imageFilterField = null;
    }

    @Test
    public void testSetImageFilterWithNonNullValue() throws Exception {
        String expectedFilter = "vintage";
        mediaFeedData.setImageFilter(expectedFilter);
        String actualFilter = (String) imageFilterField.get(mediaFeedData);
        Assert.assertEquals(expectedFilter, actualFilter);
    }

    @Test
    public void testSetImageFilterWithNullValue() throws Exception {
        mediaFeedData.setImageFilter(null);
        String actualFilter = (String) imageFilterField.get(mediaFeedData);
        Assert.assertNull(actualFilter);
    }
}
