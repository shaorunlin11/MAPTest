package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokengetSecretTest {

    @Test
    public void testGetSecret() throws Exception {
        String token = "testToken";
        String secret = "testSecret";
        Token tokenInstance = new Token(token, secret);
        assertEquals("Expected secret to be returned", secret, tokenInstance.getSecret());
    }

    @Test
    public void testGetSecretWithRawResponse() throws Exception {
        String token = "testToken";
        String secret = "testSecret";
        String rawResponse = "testRawResponse";
        Token tokenInstance = new Token(token, secret, rawResponse);
        assertEquals("Expected secret to be returned", secret, tokenInstance.getSecret());
    }
}
