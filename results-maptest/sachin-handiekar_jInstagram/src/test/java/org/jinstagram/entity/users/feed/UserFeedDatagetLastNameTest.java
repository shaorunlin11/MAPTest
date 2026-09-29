package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class UserFeedDatagetLastNameTest {
    private UserFeedData userFeedData;

    @Before
    public void setUp() {
        userFeedData = new UserFeedData();
    }

    @After
    public void tearDown() {
        userFeedData = null;
    }

    @Test
    public void testGetLastNameReturnsNullWhenNotSet() {
        Assert.assertNull(userFeedData.getLastName());
    }

    @Test
    public void testGetLastNameReturnsSetStringValue() throws Exception {
        String expectedLastName = "Doe";
        Field lastNameField = UserFeedData.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        lastNameField.set(userFeedData, expectedLastName);
        Assert.assertEquals(expectedLastName, userFeedData.getLastName());
    }
}
