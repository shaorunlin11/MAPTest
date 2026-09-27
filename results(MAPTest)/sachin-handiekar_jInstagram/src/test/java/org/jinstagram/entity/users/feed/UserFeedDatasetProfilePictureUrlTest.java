package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class UserFeedDatasetProfilePictureUrlTest {
    private UserFeedData userFeedData;
    private Field profilePictureUrlField;

    @Before
    public void setUp() throws Exception {
        userFeedData = new UserFeedData();
        profilePictureUrlField = UserFeedData.class.getDeclaredField("profilePictureUrl");
        profilePictureUrlField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        userFeedData = null;
        profilePictureUrlField = null;
    }

    @Test
    public void testSetProfilePictureUrlSetsValueCorrectly() throws Exception {
        String expectedUrl = "https://example.com/profile.jpg";
        userFeedData.setProfilePictureUrl(expectedUrl);
        String actualUrl = (String) profilePictureUrlField.get(userFeedData);
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testSetProfilePictureUrlWithNullValue() throws Exception {
        String expectedUrl = null;
        userFeedData.setProfilePictureUrl(expectedUrl);
        String actualUrl = (String) profilePictureUrlField.get(userFeedData);
        Assert.assertNull(actualUrl);
    }

    @Test
    public void testSetProfilePictureUrlWithEmptyString() throws Exception {
        String expectedUrl = "";
        userFeedData.setProfilePictureUrl(expectedUrl);
        String actualUrl = (String) profilePictureUrlField.get(userFeedData);
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
