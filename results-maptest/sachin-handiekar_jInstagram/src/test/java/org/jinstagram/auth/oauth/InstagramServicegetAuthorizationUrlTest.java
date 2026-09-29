package org.jinstagram.auth.oauth;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.jinstagram.auth.model.OAuthConfig;
import org.jinstagram.auth.InstagramApi;

public class InstagramServicegetAuthorizationUrlTest {
    private InstagramService instagramService;
    private InstagramApi mockApi;
    private OAuthConfig mockConfig;

    @Before
    public void setUp() {
        mockApi = new InstagramApi() {
            @Override
            public String getAuthorizationUrl(OAuthConfig config) {
                return "https://example.com/authorize";
            }
        };
        mockConfig = new OAuthConfig("client_id", "client_secret", "redirect_uri", "scope", "state");
        instagramService = new InstagramService(mockApi, mockConfig);
    }

    @Test
    public void testGetAuthorizationUrlDelegatesToApi() {
        String result = instagramService.getAuthorizationUrl();
        Assert.assertEquals("https://example.com/authorize", result);
    }
}
