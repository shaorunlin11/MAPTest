package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.UsersInPhoto;
import org.jinstagram.entity.common.Videos;
import java.util.List;
import java.util.ArrayList;

public class CarouselMediagetImagesTest {
    private CarouselMedia carouselMedia;

    @Before
    public void setUp() {
        carouselMedia = new CarouselMedia();
    }

    @Test
    public void testGetImagesReturnsNullWhenNotSet() {
        Images result = carouselMedia.getImages();
        Assert.assertNull(result);
    }

    @Test
    public void testGetImagesReturnsSetImages() {
        Images expectedImages = new Images();
        carouselMedia.setImages(expectedImages);

        Images result = carouselMedia.getImages();
        Assert.assertEquals(expectedImages, result);
    }
}
