package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfiggetScopeTest {

    @Test
    public void testGetScope() throws Exception {
        // Test case 1: Scope is set in constructor
        OAuthConfig config1 = new OAuthConfig("key", "secret", "callback", "scope1");
        assertEquals("scope1", config1.getScope());

        // Test case 2: Scope is null in constructor
        OAuthConfig config2 = new OAuthConfig("key", "secret", "callback", null);
        assertNull(config2.getScope());

        // Test case 3: Scope is set with display parameter
        OAuthConfig config3 = new OAuthConfig("key", "secret", "callback", "scope3", "display");
        assertEquals("scope3", config3.getScope());
    }
}
