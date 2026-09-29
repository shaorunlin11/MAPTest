package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsergetFullNameTest {

    @Test
    public void testGetFullName() {
        User user = new User();
        String expected = "John Doe";
        // Use reflection to set private field
        try {
            java.lang.reflect.Field field = User.class.getDeclaredField("fullName");
            field.setAccessible(true);
            field.set(user, expected);
        } catch (Exception e) {
            fail("Failed to set private field: " + e.getMessage());
        }
        assertEquals(expected, user.getFullName());
    }

    @Test
    public void testGetFullNameWhenNull() {
        User user = new User();
        assertNull(user.getFullName());
    }
}
