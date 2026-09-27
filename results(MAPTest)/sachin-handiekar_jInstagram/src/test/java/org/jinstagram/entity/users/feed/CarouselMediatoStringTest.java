package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Videos;

public class CarouselMediatoStringTest {
    private CarouselMedia carouselMedia;
    private Images images;
    private Videos videos;
    private List<UsersInPhoto> usersInPhotoList;

    @Before
    public void setUp() {
        carouselMedia = new CarouselMedia();
        images = new Images();
        videos = new Videos();
        usersInPhotoList = new ArrayList<UsersInPhoto>();
    }

    @After
    public void tearDown() {
        carouselMedia = null;
        images = null;
        videos = null;
        usersInPhotoList = null;
    }

    @Test
    public void testToString() {
        // Use reflection to set private fields
        try {
            java.lang.reflect.Field typeField = CarouselMedia.class.getDeclaredField("type");
            typeField.setAccessible(true);
            typeField.set(carouselMedia, "photo");

            java.lang.reflect.Field imagesField = CarouselMedia.class.getDeclaredField("images");
            imagesField.setAccessible(true);
            imagesField.set(carouselMedia, images);

            java.lang.reflect.Field videosField = CarouselMedia.class.getDeclaredField("videos");
            videosField.setAccessible(true);
            videosField.set(carouselMedia, videos);

            java.lang.reflect.Field usersInPhotoListField = CarouselMedia.class.getDeclaredField("usersInPhotoList");
            usersInPhotoListField.setAccessible(true);
            usersInPhotoListField.set(carouselMedia, usersInPhotoList);
        } catch (Exception e) {
            Assert.fail("Failed to set private fields: " + e.getMessage());
        }

        String result = carouselMedia.toString();

        Assert.assertTrue(result.contains("type=photo"));
        Assert.assertTrue(result.contains("images=" + images.toString()));
        Assert.assertTrue(result.contains("videos=" + videos.toString()));
        Assert.assertTrue(result.contains("usersInPhotoList=" + usersInPhotoList.toString()));
    }
}
