package org.jinstagram.auth.oauth;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.Instagram;
import org.jinstagram.InstagramClient;
import org.jinstagram.auth.model.OAuthConfig;
import org.jinstagram.auth.model.Token;

public class InstagramServicegetSignedHeaderInstagramTest {

    @Test
    public void testGetSignedHeaderInstagramReturnsInstagramInstance() {
        // Arrange
        OAuthConfig config = new OAuthConfig("client_id", "client_secret", "redirect_uri", "scope");
        Token accessToken = new Token("test_token", "bearer", "3600");
        String ipAddress = "127.0.0.1";

        InstagramService service = new InstagramService(new org.jinstagram.auth.InstagramApi(), config);

        // Act
        InstagramClient result = service.getSignedHeaderInstagram(accessToken, ipAddress);

        // Assert
        Assert.assertTrue(result instanceof Instagram);
    }
}
