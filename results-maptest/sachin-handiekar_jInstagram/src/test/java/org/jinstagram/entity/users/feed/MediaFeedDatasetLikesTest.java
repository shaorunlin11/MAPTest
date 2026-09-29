package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Likes;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.common.Comments;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.Location;
import org.jinstagram.entity.common.User;
import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Videos;

public class MediaFeedDatasetLikesTest {
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
    public void testSetLikesWithNonNullValue() {
        Likes likes = new Likes();
        mediaFeedData.setLikes(likes);
        Assert.assertEquals(likes, mediaFeedData.getLikes());
    }

    @Test
    public void testSetLikesWithNullValue() {
        mediaFeedData.setLikes(null);
        Assert.assertNull(mediaFeedData.getLikes());
    }
}
