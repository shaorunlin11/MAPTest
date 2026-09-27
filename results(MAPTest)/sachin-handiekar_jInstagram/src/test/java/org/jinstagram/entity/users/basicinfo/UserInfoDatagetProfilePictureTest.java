package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserInfoDatagetProfilePictureTest {

    @Test
    public void testGetProfilePicture() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedProfilePicture = "https://example.com/profile.jpg";

        // Use reflection to set the private field
        java.lang.reflect.Field profilePictureField = UserInfoData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        profilePictureField.set(userInfoData, expectedProfilePicture);

        String actualProfilePicture = userInfoData.getProfilePicture();

        assertEquals(expectedProfilePicture, actualProfilePicture);
    }
}
