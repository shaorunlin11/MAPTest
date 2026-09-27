package org.jinstagram.entity.media;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class MediaInfoFeedsetMetaTest {
    private MediaInfoFeed mediaInfoFeed;
    private Meta meta;

    @Before
    public void setUp() {
        mediaInfoFeed = new MediaInfoFeed();
        meta = new Meta();
    }

    @After
    public void tearDown() {
        mediaInfoFeed = null;
        meta = null;
    }

    @Test
    public void testSetMeta() {
        mediaInfoFeed.setMeta(meta);
        Assert.assertEquals(meta, mediaInfoFeed.getMeta());
    }
}
