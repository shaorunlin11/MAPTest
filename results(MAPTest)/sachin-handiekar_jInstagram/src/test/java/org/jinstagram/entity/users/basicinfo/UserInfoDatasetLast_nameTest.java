package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetLast_nameTest {
    @Test
    public void testSetLast_name() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedLastName = "Doe";

        userInfoData.setLast_name(expectedLastName);

        Assert.assertEquals(expectedLastName, userInfoData.getLastName());
    }
}
