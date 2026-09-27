package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDatagetFirstNameTest {
    @Test
    public void testGetFirstName() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedFirstName = "John";
        Field firstNameField = UserInfoData.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        firstNameField.set(userInfoData, expectedFirstName);

        String actualFirstName = userInfoData.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }
}
