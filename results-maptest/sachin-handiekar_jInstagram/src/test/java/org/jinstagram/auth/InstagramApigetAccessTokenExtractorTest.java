package org.jinstagram.auth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.jinstagram.auth.exceptions.OAuthException;
import org.jinstagram.auth.model.Token;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
public class InstagramApigetAccessTokenExtractorTest {
    @Test
    public void testGetAccessTokenExtractorWithValidResponse() throws Exception {
        InstagramApi instagramApi = new InstagramApi();
        AccessTokenExtractor extractor = instagramApi.getAccessTokenExtractor();

        String validResponse = "{\"access_token\": \"test_token\"}";
        Token token = extractor.extract(validResponse);

        assertEquals("test_token", token.getToken());
        assertEquals("", token.getSecret());
        assertEquals(validResponse, token.getRawResponse());
    }



    @Test
    public void testGetAccessTokenExtractorWithMissingAccessToken() {
        InstagramApi instagramApi = new InstagramApi();
        AccessTokenExtractor extractor = instagramApi.getAccessTokenExtractor();

        String invalidResponse = "{\"other_key\": \"test_value\"}";
        try {
            extractor.extract(invalidResponse);
            fail("Expected OAuthException was not thrown");
        } catch (OAuthException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetAccessTokenExtractorWithEmptyAccessToken() {
        InstagramApi instagramApi = new InstagramApi();
        AccessTokenExtractor extractor = instagramApi.getAccessTokenExtractor();

        String invalidResponse = "{\"access_token\": \"\"}";
        try {
            extractor.extract(invalidResponse);
            fail("Expected OAuthException was not thrown");
        } catch (OAuthException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetAccessTokenExtractorWithInvalidJson() {
        InstagramApi instagramApi = new InstagramApi();
        AccessTokenExtractor extractor = instagramApi.getAccessTokenExtractor();

        String invalidJson = "invalid json";
        try {
            extractor.extract(invalidJson);
            fail("Expected OAuthException was not thrown");
        } catch (OAuthException e) {
            // Expected exception
        }
    }
}
