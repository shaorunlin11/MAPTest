package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;


public class UsergetProfilePictureUrlTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testGetProfilePictureUrl_returnsExpectedValue() throws Exception {
        String expectedUrl = "https://example.com/profile.jpg";
        Field field = User.class.getDeclaredField("profilePictureUrl");
        field.setAccessible(true);
        field.set(user, expectedUrl);

        String result = user.getProfilePictureUrl();
        assertEquals(expectedUrl, result);
    }

    @Test
    public void testGetProfilePictureUrl_returnsNullWhenNotSet() throws Exception {
        Field field = User.class.getDeclaredField("profilePictureUrl");
        field.setAccessible(true);
        field.set(user, null);

        String result = user.getProfilePictureUrl();
        assertEquals(null, result);
    }
}
