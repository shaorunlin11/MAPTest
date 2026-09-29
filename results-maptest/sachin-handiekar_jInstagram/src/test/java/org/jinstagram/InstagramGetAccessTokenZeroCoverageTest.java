package org.jinstagram;

import org.junit.Test;

import org.jinstagram.auth.model.Token;


public class InstagramGetAccessTokenZeroCoverageTest {
    @Test
    public void testGetAccessToken() {
        Token accessToken = new Token("test_token", "test_secret");
        Instagram instagram = new Instagram(accessToken);

        // Ensure that the getAccessToken method returns the expected value
        assert instagram.getAccessToken() == accessToken;
    }
}
