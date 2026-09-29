package org.jinstagram;

import org.junit.Test;
import static org.junit.Assert.*;

import org.jinstagram.auth.model.Token;
import org.jinstagram.InstagramConfig;
import org.jinstagram.Instagram;

public class InstagramgetClientIdTest {

    @Test
    public void testGetClientId() throws Exception {
        // Test case 1: Create Instagram with clientId constructor
        String expectedClientId = "testClientId";
        Instagram instagram = new Instagram(expectedClientId);
        assertEquals("getClientId should return the initialized clientId", expectedClientId, instagram.getClientId());

        // Test case 2: Create Instagram with clientId and config constructor
        InstagramConfig config = new InstagramConfig();
        Instagram instagramWithConfig = new Instagram(expectedClientId, config);
        assertEquals("getClientId should return the initialized clientId", expectedClientId, instagramWithConfig.getClientId());

        // Test case 3: Create Instagram with Token and config constructor
        Token token = new Token("token", "secret");
        Instagram instagramWithTokenAndConfig = new Instagram(token, new InstagramConfig());
        assertEquals("getClientId should return the initialized clientId", null, instagramWithTokenAndConfig.getClientId());
    }
}
