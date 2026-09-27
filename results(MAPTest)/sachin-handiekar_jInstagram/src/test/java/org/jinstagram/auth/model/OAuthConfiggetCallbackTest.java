package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfiggetCallbackTest {

    @Test
    public void testGetCallbackWithCallbackSpecified() {
        OAuthConfig config = new OAuthConfig("key", "secret", "http://example.com/callback", "read");
        assertEquals("http://example.com/callback", config.getCallback());
    }

    @Test
    public void testGetCallbackWithCallbackNull() {
        OAuthConfig config = new OAuthConfig("key", "secret", null, "read");
        assertEquals(OAuthConstants.OUT_OF_BAND, config.getCallback());
    }

    @Test
    public void testGetCallbackWithDisplayParameter() {
        OAuthConfig config = new OAuthConfig("key", "secret", "http://example.com/callback", "read", "popup");
        assertEquals("http://example.com/callback", config.getCallback());
    }
}
