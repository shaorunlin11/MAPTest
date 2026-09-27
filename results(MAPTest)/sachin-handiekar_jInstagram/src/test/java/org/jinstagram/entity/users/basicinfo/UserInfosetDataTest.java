package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.InstagramObject;
import com.google.gson.annotations.SerializedName;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.basicinfo.UserInfoData;

public class UserInfosetDataTest {
    private UserInfo userInfo;

    @Before
    public void setUp() {
        userInfo = new UserInfo();
    }

    @After
    public void tearDown() {
        userInfo = null;
    }

    @Test
    public void testSetDataWithNonNullData() {
        UserInfoData testData = new UserInfoData();
        userInfo.setData(testData);
        Assert.assertEquals(testData, userInfo.getData());
    }

    @Test
    public void testSetDataWithNullData() {
        userInfo.setData(null);
        Assert.assertNull(userInfo.getData());
    }
}
