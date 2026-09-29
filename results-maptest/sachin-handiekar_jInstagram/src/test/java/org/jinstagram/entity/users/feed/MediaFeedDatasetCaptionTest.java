package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Caption;

public class MediaFeedDatasetCaptionTest {
    private MediaFeedData mediaFeedData;
    private Caption caption;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        caption = new Caption();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
        caption = null;
    }

    @Test
    public void testSetCaption() {
        mediaFeedData.setCaption(caption);
        Assert.assertEquals(caption, mediaFeedData.getCaption());
    }
}
