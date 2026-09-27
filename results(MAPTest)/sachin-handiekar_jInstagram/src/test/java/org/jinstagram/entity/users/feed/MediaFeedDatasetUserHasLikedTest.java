package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class MediaFeedDatasetUserHasLikedTest {

    private MediaFeedData mediaFeedData;
    private Field userHasLikedField;

    @Before
    public void setUp() throws Exception {
        mediaFeedData = new MediaFeedData();
        userHasLikedField = MediaFeedData.class.getDeclaredField("userHasLiked");
        userHasLikedField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        mediaFeedData = null;
        userHasLikedField = null;
    }

    @Test
    public void testSetUserHasLiked_setsValueCorrectly() throws Exception {
        boolean expectedValue = true;
        mediaFeedData.setUserHasLiked(expectedValue);

        Boolean actualValue = (Boolean) userHasLikedField.get(mediaFeedData);
        Assert.assertEquals(Boolean.valueOf(expectedValue), actualValue);
    }

    @Test
    public void testSetUserHasLiked_withFalseValue() throws Exception {
        boolean expectedValue = false;
        mediaFeedData.setUserHasLiked(expectedValue);

        Boolean actualValue = (Boolean) userHasLikedField.get(mediaFeedData);
        Assert.assertEquals(Boolean.valueOf(expectedValue), actualValue);
    }
}
