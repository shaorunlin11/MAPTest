package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Videos;

public class MediaFeedDatagetVideosTest {
    @Test
    public void testGetVideos() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        Videos expectedVideos = new Videos();

        // Set the videos field using reflection
        java.lang.reflect.Field videosField = MediaFeedData.class.getDeclaredField("videos");
        videosField.setAccessible(true);
        videosField.set(mediaFeedData, expectedVideos);

        Videos actualVideos = mediaFeedData.getVideos();
        Assert.assertEquals(expectedVideos, actualVideos);
    }

    @Test
    public void testGetVideosWhenNull() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();

        // Ensure the videos field is null
        java.lang.reflect.Field videosField = MediaFeedData.class.getDeclaredField("videos");
        videosField.setAccessible(true);
        videosField.set(mediaFeedData, null);

        Videos actualVideos = mediaFeedData.getVideos();
        Assert.assertNull(actualVideos);
    }
}
