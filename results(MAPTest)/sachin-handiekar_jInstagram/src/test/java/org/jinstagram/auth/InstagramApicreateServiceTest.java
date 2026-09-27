package org.jinstagram.auth;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.auth.model.OAuthConfig;
import org.jinstagram.auth.oauth.InstagramService;

public class InstagramApicreateServiceTest {
    @Test
    public void testCreateService() {
        InstagramApi instagramApi = new InstagramApi();
        OAuthConfig config = new OAuthConfig("clientId", "clientSecret", "redirectUri", "scope");
        InstagramService service = instagramApi.createService(config);
        Assert.assertNotNull(service);
    }
}
