package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

public class UserFeedDatagetFirstNameTest {

    @Test
    public void testGetFirstNameReturnsNullWhenNotSet() {
        UserFeedData userFeedData = new UserFeedData();
        Assert.assertNull(userFeedData.getFirstName());
    }

    @Test
    public void testGetFirstNameReturnsSetStringValue() {
        UserFeedData userFeedData = new UserFeedData();
        userFeedData.setFirstName("John");
        Assert.assertEquals("John", userFeedData.getFirstName());
    }
}
