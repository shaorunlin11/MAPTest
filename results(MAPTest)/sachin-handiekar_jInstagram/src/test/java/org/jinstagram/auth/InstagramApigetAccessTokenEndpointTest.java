package org.jinstagram.auth;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.jinstagram.auth.model.Constants;

public class InstagramApigetAccessTokenEndpointTest {
    @Test
    public void testGetAccessTokenEndpoint() {
        InstagramApi instagramApi = new InstagramApi();
        String result = instagramApi.getAccessTokenEndpoint();
        assertEquals(Constants.ACCESS_TOKEN_ENDPOINT, result);
    }
}
