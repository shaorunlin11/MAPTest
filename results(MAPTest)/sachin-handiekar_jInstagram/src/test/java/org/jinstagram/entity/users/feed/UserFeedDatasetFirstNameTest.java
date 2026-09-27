package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class UserFeedDatasetFirstNameTest {

    @Test
    public void testSetFirstNameSetsFirstNameField() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedFirstName = "John";

        userFeedData.setFirstName(expectedFirstName);

        Field firstNameField = UserFeedData.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        String actualFirstName = (String) firstNameField.get(userFeedData);

        Assert.assertEquals(expectedFirstName, actualFirstName);
    }
}
