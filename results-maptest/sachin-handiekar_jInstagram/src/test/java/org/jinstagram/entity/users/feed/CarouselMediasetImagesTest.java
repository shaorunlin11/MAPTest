package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Videos;
import java.util.List;
import java.util.ArrayList;

public class CarouselMediasetImagesTest {
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

    @Test
    public void testSetImages() {
        // Act
        carouselMedia.setImages(images);

        // Assert
        Assert.assertEquals(images, carouselMedia.getImages());
    }
}
