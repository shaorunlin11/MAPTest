package org.jinstagram.auth;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;

public class InstagramAuthServicescopeTest {
    @Test
    public void testScopeWithNonEmptyString() throws Exception {
        InstagramAuthService authService = new InstagramAuthService();
        String testScope = "user_profile";

        InstagramAuthService result = authService.scope(testScope);

        java.lang.reflect.Field scopeField = result.getClass().getDeclaredField("scope");
        scopeField.setAccessible(true);
        Assert.assertEquals(testScope, scopeField.get(result));
        Assert.assertSame(authService, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScopeWithEmptyString() throws Exception {
        InstagramAuthService authService = new InstagramAuthService();
        String emptyScope = "";

        authService.scope(emptyScope);
    }
}
