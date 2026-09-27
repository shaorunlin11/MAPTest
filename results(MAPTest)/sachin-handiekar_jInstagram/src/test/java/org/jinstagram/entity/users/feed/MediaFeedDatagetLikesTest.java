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

import java.util.List;
import java.util.ArrayList;

public class MediaFeedDatagetLikesTest {

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
    public void testGetLikesReturnsInitializedValue() {
        Likes expectedLikes = new Likes();
        mediaFeedData.setLikes(expectedLikes);

        Likes actualLikes = mediaFeedData.getLikes();

        Assert.assertEquals(expectedLikes, actualLikes);
    }

    @Test
    public void testGetLikesReturnsNullWhenNotInitialized() {
        mediaFeedData.setLikes(null);

        Likes actualLikes = mediaFeedData.getLikes();

        Assert.assertNull(actualLikes);
    }
}
