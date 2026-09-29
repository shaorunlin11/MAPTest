package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.basicinfo.UserInfoData;

public class UserInfogetDataTest {
    private UserInfo userInfo;
    private UserInfoData expectedData;

    @Before
    public void setUp() {
        userInfo = new UserInfo();
        expectedData = new UserInfoData();
        // Set up the data field using reflection to bypass private access
        try {
            java.lang.reflect.Field dataField = UserInfo.class.getDeclaredField("data");
            dataField.setAccessible(true);
            dataField.set(userInfo, expectedData);
        } catch (Exception e) {
            Assert.fail("Failed to set up test data: " + e.getMessage());
        }
    }

    @Test
    public void testGetData_ReturnsExpectedData() {
        UserInfoData result = userInfo.getData();
        Assert.assertEquals("The returned data should match the expected data", expectedData, result);
    }
}
