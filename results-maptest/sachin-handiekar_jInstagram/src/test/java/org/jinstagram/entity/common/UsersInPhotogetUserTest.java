package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class UsersInPhotogetUserTest {

    @Test
    public void testGetUser() throws Exception {
        // Create a UsersInPhoto instance
        UsersInPhoto usersInPhoto = new UsersInPhoto();

        // Create a User instance
        User user = new User();

        // Set the user field using reflection
        java.lang.reflect.Field userField = UsersInPhoto.class.getDeclaredField("user");
        userField.setAccessible(true);
        userField.set(usersInPhoto, user);

        // Call the getUser method
        User result = usersInPhoto.getUser();

        // Assert that the returned user is the same as the one set
        Assert.assertEquals(user, result);
    }
}
