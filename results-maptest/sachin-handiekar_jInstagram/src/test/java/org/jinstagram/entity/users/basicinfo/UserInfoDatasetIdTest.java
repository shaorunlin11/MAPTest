package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetIdTest {

    @Test
    public void testSetId() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedId = "1234567890";

        userInfoData.setId(expectedId);

        Assert.assertEquals(expectedId, userInfoData.getId());
    }
}
