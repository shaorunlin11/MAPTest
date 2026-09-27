package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class UserFeedDatasetUserNameTest {
    private UserFeedData userFeedData;
    private Field userNameField;

    @Before
    public void setUp() throws Exception {
        userFeedData = new UserFeedData();
        userNameField = UserFeedData.class.getDeclaredField("userName");
        userNameField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        userFeedData = null;
        userNameField = null;
    }

    @Test
    public void testSetUserName() throws Exception {
        String expectedUserName = "testUser";
        userFeedData.setUserName(expectedUserName);

        String actualUserName = (String) userNameField.get(userFeedData);
        Assert.assertEquals(expectedUserName, actualUserName);
    }
}
