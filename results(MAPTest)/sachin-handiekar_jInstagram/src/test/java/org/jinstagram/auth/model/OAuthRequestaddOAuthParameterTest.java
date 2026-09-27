package org.jinstagram.auth.model;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.jinstagram.http.Request;
import org.jinstagram.http.Verbs;
import java.util.Map;
import java.util.HashMap;

public class OAuthRequestaddOAuthParameterTest {
    private OAuthRequest oauthRequest;

    @Before
    public void setUp() {
        oauthRequest = new OAuthRequest(Verbs.GET, "http://example.com");
    }

    @Test
    public void testAddOAuthParameter() {
        String key = "oauth_testKey";
        String value = "testValue";

        oauthRequest.addOAuthParameter(key, value);

        Map<String, String> parameters = oauthRequest.getOauthParameters();
        Assert.assertEquals(value, parameters.get(key));
    }

    // Helper method to access private field for testing
    private Map<String, String> getOauthParameters() throws Exception {
        java.lang.reflect.Field field = OAuthRequest.class.getDeclaredField("oauthParameters");
        field.setAccessible(true);
        return (Map<String, String>) field.get(oauthRequest);
    }
}
