package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UserFeedDatasetFullNameTest {
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
    public void testSetFullName() throws Exception {
        String expectedFullName = "John Doe";
        userFeedData.setFullName(expectedFullName);
        Assert.assertEquals("The fullName should be set correctly", expectedFullName, userFeedData.getFullName());
    }

    @Test
    public void testSetFullNameWithNull() throws Exception {
        userFeedData.setFullName(null);
        Assert.assertNull("The fullName should be null when set with null", userFeedData.getFullName());
    }
}
