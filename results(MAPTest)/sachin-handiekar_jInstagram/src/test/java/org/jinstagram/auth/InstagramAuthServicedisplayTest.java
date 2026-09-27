package org.jinstagram.auth;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;

public class InstagramAuthServicedisplayTest {

    @Test
    public void testDisplayWithEmptyString() {
        InstagramAuthService authService = new InstagramAuthService();
        try {
            authService.display("");
            Assert.fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testDisplayWithNonEmptyString() {
        InstagramAuthService authService = new InstagramAuthService();
        String testDisplay = "testDisplay";
        InstagramAuthService result = authService.display(testDisplay);
        Assert.assertEquals(authService, result);
        Assert.assertEquals(testDisplay, getDisplayField(authService));
    }

    private String getDisplayField(InstagramAuthService authService) {
        try {
            java.lang.reflect.Field field = InstagramAuthService.class.getDeclaredField("display");
            field.setAccessible(true);
            return (String) field.get(authService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access display field", e);
        }
    }
}
