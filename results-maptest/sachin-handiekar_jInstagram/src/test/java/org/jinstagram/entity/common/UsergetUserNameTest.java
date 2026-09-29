package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class UsergetUserNameTest {
    @Test
    public void testGetUserName() throws Exception {
        User user = new User();
        String expectedUserName = "testuser";

        // Use reflection to set the private field
        Field userNameField = User.class.getDeclaredField("userName");
        userNameField.setAccessible(true);
        userNameField.set(user, expectedUserName);

        String actualUserName = user.getUserName();
        Assert.assertEquals(expectedUserName, actualUserName);
    }
}
