package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserFeedDatagetProfilePictureUrlTest {

    @Test
    public void testGetProfilePictureUrl() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedUrl = "https://example.com/profile.jpg";

        // Use reflection to set the private field
        java.lang.reflect.Field field = UserFeedData.class.getDeclaredField("profilePictureUrl");
        field.setAccessible(true);
        field.set(userFeedData, expectedUrl);

        String actualUrl = userFeedData.getProfilePictureUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testGetProfilePictureUrlWhenNull() throws Exception {
        UserFeedData userFeedData = new UserFeedData();

        String actualUrl = userFeedData.getProfilePictureUrl();
        assertNull(actualUrl);
    }
}
