package org.jinstagram.auth;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.jinstagram.http.Verbs;

public class InstagramApigetAccessTokenVerbTest {
    @Test
    public void testGetAccessTokenVerb_returnsPost() {
        InstagramApi instagramApi = new InstagramApi();
        Verbs result = instagramApi.getAccessTokenVerb();
        assertEquals(Verbs.POST, result);
    }
}
