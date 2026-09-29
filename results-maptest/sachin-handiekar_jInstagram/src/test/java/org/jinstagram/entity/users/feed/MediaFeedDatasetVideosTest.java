package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Videos;

public class MediaFeedDatasetVideosTest {
    private MediaFeedData mediaFeedData;
    private Videos videos;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        videos = new Videos();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
        videos = null;
    }

    @Test
    public void testSetVideos() {
        // Act
        mediaFeedData.setVideos(videos);

        // Assert
        Assert.assertEquals(videos, mediaFeedData.getVideos());
    }
}
