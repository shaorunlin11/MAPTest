package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.User;

public class MediaFeedDatasetUserTest {
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
    }

    @Test
    public void testSetUser() {
        User user = new User();
        mediaFeedData.setUser(user);
        Assert.assertEquals(user, mediaFeedData.getUser());
    }
}
