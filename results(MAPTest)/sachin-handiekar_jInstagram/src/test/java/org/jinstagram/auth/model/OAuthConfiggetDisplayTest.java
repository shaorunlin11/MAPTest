package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfiggetDisplayTest {

    @Test
    public void testGetDisplay_WhenDisplayIsSet_ReturnsExpectedValue() {
        String display = "test_display";
        OAuthConfig config = new OAuthConfig("key", "secret", "callback", "scope", display);
        assertEquals(display, config.getDisplay());
    }

    @Test
    public void testGetDisplay_WhenDisplayIsNotSet_ReturnsNull() {
        OAuthConfig config = new OAuthConfig("key", "secret");
        assertNull(config.getDisplay());
    }

    @Test
    public void testGetDisplay_WhenDisplayIsSetToNull_ReturnsNull() {
        OAuthConfig config = new OAuthConfig("key", "secret", "callback", "scope", null);
        assertNull(config.getDisplay());
    }
}
