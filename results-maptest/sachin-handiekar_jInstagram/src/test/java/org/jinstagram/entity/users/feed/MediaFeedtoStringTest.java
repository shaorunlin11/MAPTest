package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;

public class MediaFeedtoStringTest {

    private MediaFeed mediaFeed;

    @Before
    public void setUp() {
        mediaFeed = new MediaFeed();
        mediaFeed.setData(new ArrayList<MediaFeedData>());
        mediaFeed.setMeta(new Meta());
        mediaFeed.setPagination(new Pagination());
    }

    @After
    public void tearDown() {
        mediaFeed = null;
    }

    @Test
    public void testToString() {
        String result = mediaFeed.toString();
        Assert.assertTrue(result.contains("MediaFeed [data="));
        Assert.assertTrue(result.contains(", meta="));
        Assert.assertTrue(result.contains(", pagination="));
    }
}
