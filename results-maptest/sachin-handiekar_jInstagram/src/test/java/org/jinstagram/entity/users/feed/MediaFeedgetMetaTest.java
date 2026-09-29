package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

public class MediaFeedgetMetaTest {

    @Test
    public void testGetMetaReturnsMetaObject() throws Exception {
        MediaFeed mediaFeed = new MediaFeed();
        Meta expectedMeta = new Meta();

        // Use reflection to set the meta field
        java.lang.reflect.Field metaField = MediaFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(mediaFeed, expectedMeta);

        Meta actualMeta = mediaFeed.getMeta();
        Assert.assertEquals(expectedMeta, actualMeta);
    }

    @Test
    public void testGetMetaReturnsNullWhenMetaNotSet() throws Exception {
        MediaFeed mediaFeed = new MediaFeed();

        // Use reflection to ensure meta is not set
        java.lang.reflect.Field metaField = MediaFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(mediaFeed, null);

        Meta actualMeta = mediaFeed.getMeta();
        Assert.assertNull(actualMeta);
    }
}
