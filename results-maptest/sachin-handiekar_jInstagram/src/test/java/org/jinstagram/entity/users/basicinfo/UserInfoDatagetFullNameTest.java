package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserInfoDatagetFullNameTest {
    @Test
    public void testGetFullName() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedFullName = "John Doe";

        // Use reflection to set the private field
        java.lang.reflect.Field fullNameField = UserInfoData.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        fullNameField.set(userInfoData, expectedFullName);

        String actualFullName = userInfoData.getFullName();

        assertEquals(expectedFullName, actualFullName);
    }

    @Test
    public void testGetFullNameWithNull() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedFullName = null;

        // Use reflection to set the private field
        java.lang.reflect.Field fullNameField = UserInfoData.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        fullNameField.set(userInfoData, expectedFullName);

        String actualFullName = userInfoData.getFullName();

        assertNull(actualFullName);
    }
}
