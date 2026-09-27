package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDatagetLastNameTest {

    @Test
    public void testGetLastName() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedLastName = "Doe";
        Field lastNameField = UserInfoData.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        lastNameField.set(userInfoData, expectedLastName);

        String actualLastName = userInfoData.getLastName();
        assertEquals(expectedLastName, actualLastName);
    }
}
