package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.User;

public class MediaFeedDatagetUserTest {
    private MediaFeedData mediaFeedData;
    private User testUser;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        testUser = new User();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
        testUser = null;
    }

    @Test
    public void testGetUser_ReturnsSetUser() {
        mediaFeedData.setUser(testUser);
        User result = mediaFeedData.getUser();
        Assert.assertEquals(testUser, result);
    }

    @Test
    public void testGetUser_ReturnsNullWhenUserNotSet() {
        mediaFeedData.setUser(null);
        User result = mediaFeedData.getUser();
        Assert.assertNull(result);
    }
}
