package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.Videos;

public class CarouselMediasetUsersInPhotoListTest {
    private CarouselMedia carouselMedia;
    private List<UsersInPhoto> usersInPhotoList;

    @Before
    public void setUp() {
        carouselMedia = new CarouselMedia();
        usersInPhotoList = new ArrayList<UsersInPhoto>();
        usersInPhotoList.add(new UsersInPhoto());
    }

    @After
    public void tearDown() {
        carouselMedia = null;
        usersInPhotoList = null;
    }

    @Test
    public void testSetUsersInPhotoList() throws Exception {
        // Act
        carouselMedia.setUsersInPhotoList(usersInPhotoList);

        // Assert
        Assert.assertEquals(usersInPhotoList, carouselMedia.getUsersInPhotoList());
    }
}
