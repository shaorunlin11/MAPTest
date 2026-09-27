package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfighasScopeTest {

    @Test
    public void testHasScopeWhenScopeIsSet() throws Exception {
        OAuthConfig config = new OAuthConfig("key", "secret", "callback", "read");
        assertTrue(config.hasScope());
    }

    @Test
    public void testHasScopeWhenScopeIsNull() throws Exception {
        OAuthConfig config = new OAuthConfig("key", "secret", "callback", null);
        assertFalse(config.hasScope());
    }

    @Test
    public void testHasScopeWithDisplayParameter() throws Exception {
        OAuthConfig config = new OAuthConfig("key", "secret", "callback", "write", "mobile");
        assertTrue(config.hasScope());
    }

    @Test
    public void testHasScopeWithNullScopeAndCallback() throws Exception {
        OAuthConfig config = new OAuthConfig("key", "secret", null, null);
        assertFalse(config.hasScope());
    }
}
