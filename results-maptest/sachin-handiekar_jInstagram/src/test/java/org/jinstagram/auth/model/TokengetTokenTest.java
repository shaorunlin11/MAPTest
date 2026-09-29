package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokengetTokenTest {

    @Test
    public void testGetToken() throws Exception {
        String expectedToken = "testToken";
        String expectedSecret = "testSecret";
        String expectedRawResponse = "testRawResponse";

        Token tokenInstance = new Token(expectedToken, expectedSecret, expectedRawResponse);
        String actualToken = tokenInstance.getToken();

        assertEquals("getToken should return the correct token value", expectedToken, actualToken);
    }

    @Test
    public void testGetTokenWithNullRawResponse() throws Exception {
        String expectedToken = "testToken";
        String expectedSecret = "testSecret";
        String expectedRawResponse = null;

        Token tokenInstance = new Token(expectedToken, expectedSecret, expectedRawResponse);
        String actualToken = tokenInstance.getToken();

        assertEquals("getToken should return the correct token value even with null rawResponse", expectedToken, actualToken);
    }
}
