package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetUsernameTest {
    @Test
    public void testSetUsername() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedUsername = "testuser";

        userInfoData.setUsername(expectedUsername);

        Assert.assertEquals("Username should be set correctly", expectedUsername, userInfoData.getUsername());
    }
}
