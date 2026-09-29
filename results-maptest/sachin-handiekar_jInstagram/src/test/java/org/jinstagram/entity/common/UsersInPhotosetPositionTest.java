package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UsersInPhotosetPositionTest {
    private UsersInPhoto usersInPhoto;
    private GridPosition position1;
    private GridPosition position2;

    @Before
    public void setUp() {
        usersInPhoto = new UsersInPhoto();
        position1 = new GridPosition();
        position2 = new GridPosition();
    }

    @After
    public void tearDown() {
        usersInPhoto = null;
        position1 = null;
        position2 = null;
    }

    @Test
    public void testSetPositionSetsPositionCorrectly() {
        usersInPhoto.setPosition(position1);
        Assert.assertEquals(position1, usersInPhoto.getPosition());
    }

    @Test
    public void testSetPositionWithDifferentPosition() {
        usersInPhoto.setPosition(position1);
        usersInPhoto.setPosition(position2);
        Assert.assertEquals(position2, usersInPhoto.getPosition());
    }
}
