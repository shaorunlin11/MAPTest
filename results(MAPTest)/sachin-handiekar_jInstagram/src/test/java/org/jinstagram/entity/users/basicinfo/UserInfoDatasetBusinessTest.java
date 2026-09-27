package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetBusinessTest {

    @Test
    public void testSetBusiness() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        boolean expectedValue = true;
        userInfoData.setBusiness(expectedValue);
        Assert.assertEquals(expectedValue, userInfoData.isBusiness());
    }
}
