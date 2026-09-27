package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.User;
import org.jinstagram.entity.common.GridPosition;
import org.jinstagram.entity.common.UsersInPhoto;

public class UsersInPhotosetUserTest {
    private UsersInPhoto usersInPhoto;
    private User user;
    private GridPosition position;

    @Before
    public void setUp() {
        usersInPhoto = new UsersInPhoto();
        user = new User();
        position = new GridPosition();
    }

    @Test
    public void testSetUser() {
        usersInPhoto.setUser(user);
        Assert.assertEquals(user, usersInPhoto.getUser());
    }
}
