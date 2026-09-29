package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class UsersetUserNameTest {
    @Test
    public void testSetUserName() throws Exception {
        User user = new User();
        String expectedUserName = "testUser";

        user.setUserName(expectedUserName);

        Assert.assertEquals("The userName should be set correctly", expectedUserName, user.getUserName());
    }
}
