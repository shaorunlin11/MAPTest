package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetFirstNameTest {
    @Test
    public void testSetFirstName() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedFirstName = "John";

        userInfoData.setFirstName(expectedFirstName);

        java.lang.reflect.Field firstNameField = UserInfoData.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        String actualFirstName = (String) firstNameField.get(userInfoData);

        Assert.assertEquals(expectedFirstName, actualFirstName);
    }
}
