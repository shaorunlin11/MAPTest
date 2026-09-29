package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class UsersetFullNameTest {

    @Test
    public void testSetFullName() throws Exception {
        User user = new User();
        String expectedFullName = "John Doe";

        user.setFullName(expectedFullName);

        Field fullNameField = User.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        String actualFullName = (String) fullNameField.get(user);

        Assert.assertEquals(expectedFullName, actualFullName);
    }

    @Test
    public void testSetFullNameWithNull() throws Exception {
        User user = new User();
        String expectedFullName = null;

        user.setFullName(expectedFullName);

        Field fullNameField = User.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        String actualFullName = (String) fullNameField.get(user);

        Assert.assertEquals(expectedFullName, actualFullName);
    }
}
