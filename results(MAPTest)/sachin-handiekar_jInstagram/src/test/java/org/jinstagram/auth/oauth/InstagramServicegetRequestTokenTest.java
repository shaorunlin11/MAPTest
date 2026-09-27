package org.jinstagram.auth.oauth;

import org.junit.Test;
import static org.junit.Assert.*;

public class InstagramServicegetRequestTokenTest {

    @Test
    public void testGetRequestTokenThrowsUnsupportedOperationException() {
        org.jinstagram.auth.InstagramApi api = new org.jinstagram.auth.InstagramApi();
        org.jinstagram.auth.model.OAuthConfig config = new org.jinstagram.auth.model.OAuthConfig("consumerKey", "consumerSecret");
        org.jinstagram.auth.oauth.InstagramService service = new org.jinstagram.auth.oauth.InstagramService(api, config);
        try {
            service.getRequestToken();
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (java.lang.UnsupportedOperationException e) {
            assertEquals("Unsupported operation, please use 'getAuthorizationUrl' and redirect your users there", e.getMessage());
        }
    }
}
