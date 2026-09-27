package org.jinstagram.entity.media;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.feed.MediaFeedData;

public class MediaInfoFeedgetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        MediaInfoFeed mediaInfoFeed = new MediaInfoFeed();
        Meta expectedMeta = new Meta();

        // Use reflection to set the private 'meta' field
        java.lang.reflect.Field metaField = MediaInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(mediaInfoFeed, expectedMeta);

        Meta actualMeta = mediaInfoFeed.getMeta();
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
