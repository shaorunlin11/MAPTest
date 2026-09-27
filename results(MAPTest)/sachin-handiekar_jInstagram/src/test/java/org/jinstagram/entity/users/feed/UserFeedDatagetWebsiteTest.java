package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

public class UserFeedDatagetWebsiteTest {

    @Test
    public void testGetWebsiteReturnsExpectedValue() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedWebsite = "https://example.com";

        // Use reflection to set the private field
        java.lang.reflect.Field field = UserFeedData.class.getDeclaredField("website");
        field.setAccessible(true);
        field.set(userFeedData, expectedWebsite);

        String actualWebsite = userFeedData.getWebsite();

        Assert.assertEquals(expectedWebsite, actualWebsite);
    }

    @Test
    public void testGetWebsiteReturnsNullWhenNotSet() throws Exception {
        UserFeedData userFeedData = new UserFeedData();

        String actualWebsite = userFeedData.getWebsite();

        Assert.assertNull(actualWebsite);
    }
}
