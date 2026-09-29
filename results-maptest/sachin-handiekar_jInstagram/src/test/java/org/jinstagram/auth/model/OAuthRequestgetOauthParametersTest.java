package org.jinstagram.auth.model;

import org.jinstagram.http.Request;
import org.jinstagram.http.Verbs;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OAuthRequestgetOauthParametersTest {

    @Test
    public void testGetOauthParameters_returnsInitializedMap() throws Exception {
        // Arrange
        OAuthRequest oauthRequest = new OAuthRequest(Verbs.GET, "http://example.com");

        // Act
        Map<String, String> result = oauthRequest.getOauthParameters();

        // Assert
        assertNotNull("The returned map should not be null", result);
        assertEquals("The map should be empty initially", 0, result.size());
    }

    @Test
    public void testGetOauthParameters_returnsSameInstance() throws Exception {
        // Arrange
        OAuthRequest oauthRequest = new OAuthRequest(Verbs.GET, "http://example.com");
        Map<String, String> expected = new HashMap<String, String>();
        expected.put("oauth_token", "12345");

        // Act
        oauthRequest.getOauthParameters().putAll(expected);
        Map<String, String> result = oauthRequest.getOauthParameters();

        // Assert
        assertEquals("The map should contain the added parameter", expected, result);
    }
}
