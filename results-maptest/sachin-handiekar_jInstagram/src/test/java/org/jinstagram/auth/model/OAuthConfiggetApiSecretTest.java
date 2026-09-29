package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfiggetApiSecretTest {

    @Test
    public void testGetApiSecret() throws Exception {
        String apiKey = "testKey";
        String apiSecret = "testSecret";
        OAuthConfig config = new OAuthConfig(apiKey, apiSecret);
        assertEquals("Expected apiSecret to be returned", apiSecret, config.getApiSecret());
    }

    @Test
    public void testGetApiSecretWithCallbackAndScope() throws Exception {
        String apiKey = "testKey";
        String apiSecret = "testSecret";
        String callback = "http://example.com/callback";
        String scope = "read";
        OAuthConfig config = new OAuthConfig(apiKey, apiSecret, callback, scope);
        assertEquals("Expected apiSecret to be returned", apiSecret, config.getApiSecret());
    }

    @Test
    public void testGetApiSecretWithAllParameters() throws Exception {
        String apiKey = "testKey";
        String apiSecret = "testSecret";
        String callback = "http://example.com/callback";
        String scope = "read";
        String display = "page";
        OAuthConfig config = new OAuthConfig(apiKey, apiSecret, callback, scope, display);
        assertEquals("Expected apiSecret to be returned", apiSecret, config.getApiSecret());
    }
}
