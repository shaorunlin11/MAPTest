package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthConfiggetApiKeyTest {

    @Test
    public void testGetApiKey() throws Exception {
        String apiKey = "testApiKey";
        String apiSecret = "testApiSecret";
        String callback = "http://example.com/callback";
        String scope = "read";
        String display = "page";

        OAuthConfig config = new OAuthConfig(apiKey, apiSecret, callback, scope, display);
        assertEquals("Expected the apiKey to be returned", apiKey, config.getApiKey());
    }

    @Test
    public void testGetApiKeyWithNullCallback() throws Exception {
        String apiKey = "testApiKey";
        String apiSecret = "testApiSecret";
        String scope = "read";

        OAuthConfig config = new OAuthConfig(apiKey, apiSecret, null, scope);
        assertEquals("Expected the apiKey to be returned", apiKey, config.getApiKey());
    }

    @Test
    public void testGetApiKeyWithDefaultCallback() throws Exception {
        String apiKey = "testApiKey";
        String apiSecret = "testApiSecret";
        String scope = "read";

        OAuthConfig config = new OAuthConfig(apiKey, apiSecret, null, scope);
        assertEquals("Expected the apiKey to be returned", apiKey, config.getApiKey());
    }
}
