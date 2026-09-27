package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UserInfoDatasetFullNameTest {
    private UserInfoData userInfoData;

    @Before
    public void setUp() {
        userInfoData = new UserInfoData();
    }

    @After
    public void tearDown() {
        userInfoData = null;
    }

    @Test
    public void testSetFullName() {
        String expectedFullName = "John Doe";
        userInfoData.setFullName(expectedFullName);
        Assert.assertEquals(expectedFullName, userInfoData.getFullName());
    }
}
