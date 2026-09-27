package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UsersetWebsiteUrlTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetWebsiteUrl() throws Exception {
        String expectedWebsiteUrl = "https://example.com";
        user.setWebsiteUrl(expectedWebsiteUrl);

        // Use reflection to verify the field was set correctly
        java.lang.reflect.Field websiteUrlField = User.class.getDeclaredField("websiteUrl");
        websiteUrlField.setAccessible(true);
        String actualWebsiteUrl = (String) websiteUrlField.get(user);

        assertEquals(expectedWebsiteUrl, actualWebsiteUrl);
    }
}
