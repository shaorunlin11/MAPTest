package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UsertoStringTest {

    @Test
    public void testToStringWithAllFieldsSet() {
        User user = new User();
        setUserField(user, "bio", "Software Developer");
        setUserField(user, "fullName", "John Doe");
        setUserField(user, "id", "123456");
        setUserField(user, "profilePictureUrl", "https://example.com/profile.jpg");
        setUserField(user, "userName", "johndoe");
        setUserField(user, "websiteUrl", "https://johndoe.com");

        String result = user.toString();
        String expected = "User [bio=Software Developer, fullName=John Doe, id=123456, profilePictureUrl=https://example.com/profile.jpg, userName=johndoe, websiteUrl=https://johndoe.com]";
        assertEquals(expected, result);
    }

    @Test
    public void testToStringWithNullFields() {
        User user = new User();

        String result = user.toString();
        String expected = "User [bio=null, fullName=null, id=null, profilePictureUrl=null, userName=null, websiteUrl=null]";
        assertEquals(expected, result);
    }

    private void setUserField(User user, String fieldName, String value) {
        try {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, value);
        } catch (Exception e) {
            fail("Failed to set field " + fieldName + ": " + e.getMessage());
        }
    }
}
