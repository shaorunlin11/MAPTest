package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class FromTagDatatoStringTest {
    @Test
    public void testToString() throws Exception {
        FromTagData fromTagData = new FromTagData();
        // Use reflection to set private fields
        Field fullNameField = FromTagData.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        fullNameField.set(fromTagData, "Test User");

        Field idField = FromTagData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(fromTagData, "123456");

        Field profilePictureField = FromTagData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        profilePictureField.set(fromTagData, "http://example.com/profile.jpg");

        Field usernameField = FromTagData.class.getDeclaredField("username");
        usernameField.setAccessible(true);
        usernameField.set(fromTagData, "testuser");

        String result = fromTagData.toString();
        assertEquals("FromTagData [fullName=Test User, id=123456, profilePicture=http://example.com/profile.jpg, username=testuser]", result);
    }
}
