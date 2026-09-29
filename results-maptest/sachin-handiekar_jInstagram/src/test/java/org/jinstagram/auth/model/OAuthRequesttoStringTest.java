package org.jinstagram.auth.model;

import org.jinstagram.http.Request;
import org.jinstagram.http.Verbs;
import org.junit.Test;
import static org.junit.Assert.*;

public class OAuthRequesttoStringTest {
    @Test
    public void testToString() throws Exception {
        // Arrange
        Verbs verb = Verbs.GET;
        String url = "https://api.example.com/resource";
        OAuthRequest request = new OAuthRequest(verb, url);

        // Act
        String result = request.toString();

        // Assert
        assertEquals("@OAuthRequest(GET, https://api.example.com/resource)", result);
    }
}
