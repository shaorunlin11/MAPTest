package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatagetIdTest {
    @Test
    public void testGetId() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedId = "1234567890";
        userInfoData.setId(expectedId);

        String actualId = userInfoData.getId();

        Assert.assertEquals(expectedId, actualId);
    }
}
