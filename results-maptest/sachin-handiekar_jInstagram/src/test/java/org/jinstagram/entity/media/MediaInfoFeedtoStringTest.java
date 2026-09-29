package org.jinstagram.entity.media;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.feed.MediaFeedData;

public class MediaInfoFeedtoStringTest {
    private MediaInfoFeed mediaInfoFeed;
    private MediaFeedData data;
    private Meta meta;

    @Before
    public void setUp() {
        mediaInfoFeed = new MediaInfoFeed();
        data = new MediaFeedData();
        meta = new Meta();
    }

    @After
    public void tearDown() {
        mediaInfoFeed = null;
        data = null;
        meta = null;
    }

    @Test
    public void testToString() {
        mediaInfoFeed.setData(data);
        mediaInfoFeed.setMeta(meta);

        String result = mediaInfoFeed.toString();

        Assert.assertTrue("The toString() should contain 'MediaInfoFeed'", result.contains("MediaInfoFeed"));
        Assert.assertTrue("The toString() should contain 'data'", result.contains("data="));
        Assert.assertTrue("The toString() should contain 'meta'", result.contains("meta="));
    }
}
