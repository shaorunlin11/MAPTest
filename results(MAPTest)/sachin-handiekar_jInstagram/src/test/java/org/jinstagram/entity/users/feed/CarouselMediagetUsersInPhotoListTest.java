package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Videos;
import java.util.List;
import java.util.ArrayList;

public class CarouselMediagetUsersInPhotoListTest {
    private CarouselMedia carouselMedia;
    private List<UsersInPhoto> usersInPhotoList;

    @Before
    public void setUp() {
        carouselMedia = new CarouselMedia();
        usersInPhotoList = new ArrayList<UsersInPhoto>();
        UsersInPhoto user1 = new UsersInPhoto();
        UsersInPhoto user2 = new UsersInPhoto();
        usersInPhotoList.add(user1);
        usersInPhotoList.add(user2);
    }

    @Test
    public void testGetUsersInPhotoListReturnsNonNullListWhenInitialized() {
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field field = CarouselMedia.class.getDeclaredField("usersInPhotoList");
            field.setAccessible(true);
            field.set(carouselMedia, usersInPhotoList);
        } catch (Exception e) {
            Assert.fail("Failed to set usersInPhotoList: " + e.getMessage());
        }

        List<UsersInPhoto> result = carouselMedia.getUsersInPhotoList();
        Assert.assertNotNull(result);
        Assert.assertEquals(usersInPhotoList.size(), result.size());
    }

    @Test
    public void testGetUsersInPhotoListReturnsNullWhenNotInitialized() {
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field field = CarouselMedia.class.getDeclaredField("usersInPhotoList");
            field.setAccessible(true);
            field.set(carouselMedia, null);
        } catch (Exception e) {
            Assert.fail("Failed to set usersInPhotoList: " + e.getMessage());
        }

        List<UsersInPhoto> result = carouselMedia.getUsersInPhotoList();
        Assert.assertNull(result);
    }
}
