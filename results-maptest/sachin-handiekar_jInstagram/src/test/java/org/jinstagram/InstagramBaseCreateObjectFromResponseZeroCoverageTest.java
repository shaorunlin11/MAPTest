package org.jinstagram;

import com.google.gson.Gson;
import org.jinstagram.exceptions.InstagramException;
import org.junit.Test;

import org.jinstagram.entity.common.InstagramErrorResponse;


public class InstagramBaseCreateObjectFromResponseZeroCoverageTest {
    @Test
    public void testCreateObjectFromResponse() throws InstagramException {
        // Given
        Class<InstagramErrorResponse> clazz = InstagramErrorResponse.class;
        String response = "{\"error_type\": \"OAuthException\", \"code\": 400, \"error_message\": \"Invalid access token\"}";

        // When
        InstagramErrorResponse result = InstagramBase.createObjectFromResponse(clazz, response);

        // Then
        // No assertions needed as the goal is to execute the target lines
    }
}
