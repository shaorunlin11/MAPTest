package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class UserFeedDatasetIdTest {

    @Test
    public void testSetId() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedId = "1234567890";

        // Call the method under test
        userFeedData.setId(expectedId);

        // Use reflection to verify the field was set correctly
        Field idField = UserFeedData.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(userFeedData);

        Assert.assertEquals("The id should be set correctly", expectedId, actualId);
    }
}
