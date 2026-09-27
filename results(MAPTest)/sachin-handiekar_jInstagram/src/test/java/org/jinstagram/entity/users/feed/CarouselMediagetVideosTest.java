package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Videos;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.common.UsersInPhoto;
import java.util.List;
import java.lang.reflect.Field;

public class CarouselMediagetVideosTest {

    @Test
    public void testGetVideosReturnsInitializedVideos() throws Exception {
        CarouselMedia carouselMedia = new CarouselMedia();
        Videos expectedVideos = new Videos();

        // Use reflection to set private field
        Field videosField = CarouselMedia.class.getDeclaredField("videos");
        videosField.setAccessible(true);
        videosField.set(carouselMedia, expectedVideos);

        Videos result = carouselMedia.getVideos();

        Assert.assertEquals(expectedVideos, result);
    }

    @Test
    public void testGetVideosReturnsNullWhenNotInitialized() {
        CarouselMedia carouselMedia = new CarouselMedia();

        Videos result = carouselMedia.getVideos();

        Assert.assertNull(result);
    }
}
