package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetProfilePictureTest {
    @Test
    public void testSetProfilePicture() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedProfilePicture = "https://example.com/profile.jpg";

        userInfoData.setProfilePicture(expectedProfilePicture);

        // Use reflection to verify the field was set correctly
        java.lang.reflect.Field profilePictureField = UserInfoData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        String actualProfilePicture = (String) profilePictureField.get(userInfoData);

        Assert.assertEquals(expectedProfilePicture, actualProfilePicture);
    }

    @Test
    public void testSetProfilePictureWithNull() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedProfilePicture = null;

        userInfoData.setProfilePicture(expectedProfilePicture);

        // Use reflection to verify the field was set correctly
        java.lang.reflect.Field profilePictureField = UserInfoData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        String actualProfilePicture = (String) profilePictureField.get(userInfoData);

        Assert.assertEquals(expectedProfilePicture, actualProfilePicture);
    }
}
