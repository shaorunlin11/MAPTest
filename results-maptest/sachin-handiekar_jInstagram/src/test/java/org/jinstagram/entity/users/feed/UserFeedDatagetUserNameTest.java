package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserFeedDatagetUserNameTest {

    @Test
    public void testGetUserName_ReturnsNullWhenNotSet() {
        UserFeedData userFeedData = new UserFeedData();
        assertNull(userFeedData.getUserName());
    }

    @Test
    public void testGetUserName_ReturnsSetUserName() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedUserName = "testuser";
        Field userNameField = UserFeedData.class.getDeclaredField("userName");
        userNameField.setAccessible(true);
        userNameField.set(userFeedData, expectedUserName);
        assertEquals(expectedUserName, userFeedData.getUserName());
    }
}
