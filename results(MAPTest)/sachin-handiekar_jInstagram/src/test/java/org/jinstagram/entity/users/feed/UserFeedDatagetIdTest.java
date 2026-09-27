package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class UserFeedDatagetIdTest {
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
    public void testGetId_ReturnsExpectedValue() throws Exception {
        String expectedId = "1234567890";
        Field idField = UserFeedData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(userFeedData, expectedId);

        String actualId = userFeedData.getId();
        Assert.assertEquals(expectedId, actualId);
    }
}
