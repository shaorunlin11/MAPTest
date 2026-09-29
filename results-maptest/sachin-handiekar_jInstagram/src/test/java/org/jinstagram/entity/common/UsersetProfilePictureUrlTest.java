package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UsersetProfilePictureUrlTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testSetProfilePictureUrl() {
        String expectedUrl = "https://example.com/profile.jpg";
        user.setProfilePictureUrl(expectedUrl);
        Assert.assertEquals(expectedUrl, user.getProfilePictureUrl());
    }
}
