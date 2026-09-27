package org.jinstagram.auth.oauth;

import org.junit.Test;
import org.jinstagram.auth.model.Token;
import org.jinstagram.InstagramClient;

public class InstagramServiceGetInstagramZeroCoverageTest {
    @Test
    public void testGetInstagramWithNonNullAccessToken() {
        // Arrange
        InstagramService service = new InstagramService(null, null);
        Token accessToken = new Token("testToken", "testSecret");

        // Act
        InstagramClient client = service.getInstagram(accessToken);

        // Assert
        // This test ensures that the method is called and returns a non-null value
        // as per the requirement to execute target line 104.
        assert client != null;
    }
}
