package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;

public class UserInfogetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        // Create a UserInfo instance with a Meta object
        UserInfo userInfo = new UserInfo();
        Meta expectedMeta = new Meta();

        // Use reflection to set the meta field
        java.lang.reflect.Field metaField = UserInfo.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(userInfo, expectedMeta);

        // Call the getMeta method
        Meta actualMeta = userInfo.getMeta();

        // Assert that the returned Meta object matches the expected one
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
