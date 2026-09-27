package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class UsergetWebsiteUrlTest {
    @Test
    public void testGetWebsiteUrl() throws Exception {
        User user = new User();
        String expectedWebsiteUrl = "https://example.com";
        Field websiteUrlField = User.class.getDeclaredField("websiteUrl");
        websiteUrlField.setAccessible(true);
        websiteUrlField.set(user, expectedWebsiteUrl);

        String actualWebsiteUrl = user.getWebsiteUrl();
        Assert.assertEquals(expectedWebsiteUrl, actualWebsiteUrl);
    }
}
