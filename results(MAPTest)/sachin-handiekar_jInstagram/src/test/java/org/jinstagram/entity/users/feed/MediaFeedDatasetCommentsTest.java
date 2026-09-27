package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Comments;

public class MediaFeedDatasetCommentsTest {
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
    }

    @Test
    public void testSetComments() {
        Comments expectedComments = new Comments();

        mediaFeedData.setComments(expectedComments);

        Assert.assertEquals(expectedComments, mediaFeedData.getComments());
    }
}
