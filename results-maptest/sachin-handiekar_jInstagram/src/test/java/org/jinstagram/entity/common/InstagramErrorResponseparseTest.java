package org.jinstagram.entity.common;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.jinstagram.exceptions.InstagramBadRequestException;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.exceptions.InstagramRateLimitException;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;
public class InstagramErrorResponseparseTest {
    @Test
    public void testParseWithValidJsonAndMeta() {
        Gson gson = new Gson();
        String json = "{\"meta\":{\"code\":400, \"error_type\":\"invalid_request\"}}";
        InstagramErrorResponse response = InstagramErrorResponse.parse(gson, json);
        assertNotNull(response);
        assertNotNull(getErrorMeta(response));
        assertEquals(400, getErrorMeta(response).getCode());
        assertEquals("invalid_request", getErrorMeta(response).getErrorType());
    }







    private Meta getErrorMeta(InstagramErrorResponse response) {
        try {
            java.lang.reflect.Field field = InstagramErrorResponse.class.getDeclaredField("errorMeta");
            field.setAccessible(true);
            return (Meta) field.get(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
