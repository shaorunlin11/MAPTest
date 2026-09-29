package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserFeedDatatoStringTest {

    @Test
    public void testToStringWithNonNullFields() {
        UserFeedData userFeedData = new UserFeedData();
        setUserFeedDataField(userFeedData, "id", "12345");
        setUserFeedDataField(userFeedData, "profilePictureUrl", "http://example.com/pic.jpg");
        setUserFeedDataField(userFeedData, "userName", "johndoe");
        setUserFeedDataField(userFeedData, "fullName", "John Doe");
        setUserFeedDataField(userFeedData, "website", "http://johndoe.com");
        setUserFeedDataField(userFeedData, "bio", "Software developer");

        String result = userFeedData.toString();
        assertEquals("UserFeedData [id=12345, profilePictureUrl=http://example.com/pic.jpg, userName=johndoe, fullName=John Doe, website=http://johndoe.com, bio=Software developer]", result);
    }

    @Test
    public void testToStringWithNullFields() {
        UserFeedData userFeedData = new UserFeedData();

        String result = userFeedData.toString();
        assertEquals("UserFeedData [id=null, profilePictureUrl=null, userName=null, fullName=null, website=null, bio=null]", result);
    }

    private void setUserFeedDataField(UserFeedData obj, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = UserFeedData.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            fail("Failed to set field " + fieldName + ": " + e.getMessage());
        }
    }
}
