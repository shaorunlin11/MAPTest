package org.jinstagram.auth;

import org.junit.Test;
import org.jinstagram.auth.model.OAuthConfig;
import org.jinstagram.auth.model.Constants;
import org.jinstagram.http.URLUtils;
import org.jinstagram.auth.exceptions.OAuthException;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.utils.Preconditions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InstagramApiGetAuthorizationUrlZeroCoverageTest {
    @Test
    public void testGetAuthorizationUrlWithScope() throws OAuthException {
        // Arrange
        InstagramApi instagramApi = new InstagramApi();
        OAuthConfig config = mock(OAuthConfig.class);

        when(config.getCallback()).thenReturn("https://example.com/callback");
        when(config.hasScope()).thenReturn(true);
        when(config.getApiKey()).thenReturn("test-api-key");
        when(config.getScope()).thenReturn("user_media");

        // Act
        String result = instagramApi.getAuthorizationUrl(config);

        // Assert
        String expectedUrl = String.format(Constants.SCOPED_AUTHORIZE_URL, "test-api-key", URLUtils.formURLEncode("https://example.com/callback"), "user_media");
        assertEquals(expectedUrl, result);
    }

    @Test
    public void testGetAuthorizationUrlWithoutScope() throws OAuthException {
        // Arrange
        InstagramApi instagramApi = new InstagramApi();
        OAuthConfig config = mock(OAuthConfig.class);

        when(config.getCallback()).thenReturn("https://example.com/callback");
        when(config.hasScope()).thenReturn(false);
        when(config.getApiKey()).thenReturn("test-api-key");

        // Act
        String result = instagramApi.getAuthorizationUrl(config);

        // Assert
        String expectedUrl = String.format(Constants.AUTHORIZE_URL, "test-api-key", URLUtils.formURLEncode("https://example.com/callback"));
        assertEquals(expectedUrl, result);
    }
}
