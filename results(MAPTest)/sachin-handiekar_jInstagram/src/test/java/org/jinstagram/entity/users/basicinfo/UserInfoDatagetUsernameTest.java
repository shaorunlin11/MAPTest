package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDatagetUsernameTest {
    @Test
    public void testGetUsername() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedUsername = "testuser";
        Field usernameField = UserInfoData.class.getDeclaredField("username");
        usernameField.setAccessible(true);
        usernameField.set(userInfoData, expectedUsername);

        String actualUsername = userInfoData.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }
}
