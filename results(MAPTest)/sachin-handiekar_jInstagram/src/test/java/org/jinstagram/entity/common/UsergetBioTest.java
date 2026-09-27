package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UsergetBioTest {

    @Test
    public void testGetBio_returnsBioField() throws Exception {
        User user = new User();
        String expectedBio = "This is a test bio.";
        Field bioField = User.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(user, expectedBio);

        String actualBio = user.getBio();
        assertEquals(expectedBio, actualBio);
    }

    @Test
    public void testGetBio_returnsNullIfBioNotSet() throws Exception {
        User user = new User();
        Field bioField = User.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(user, null);

        String actualBio = user.getBio();
        assertNull(actualBio);
    }
}
