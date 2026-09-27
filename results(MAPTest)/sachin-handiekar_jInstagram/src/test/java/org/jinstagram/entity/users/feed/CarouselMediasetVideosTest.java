package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Videos;

import java.lang.reflect.Field;


public class CarouselMediasetVideosTest {
    @Test
    public void testSetVideos() throws Exception {
        CarouselMedia carouselMedia = new CarouselMedia();
        Videos expectedVideos = new Videos();

        carouselMedia.setVideos(expectedVideos);

        Field videosField = CarouselMedia.class.getDeclaredField("videos");
        videosField.setAccessible(true);
        Videos actualVideos = (Videos) videosField.get(carouselMedia);

        Assert.assertEquals(expectedVideos, actualVideos);
    }

    @Test
    public void testSetVideosWithNull() throws Exception {
        CarouselMedia carouselMedia = new CarouselMedia();
        Videos expectedVideos = null;

        carouselMedia.setVideos(expectedVideos);

        Field videosField = CarouselMedia.class.getDeclaredField("videos");
        videosField.setAccessible(true);
        Videos actualVideos = (Videos) videosField.get(carouselMedia);

        Assert.assertEquals(expectedVideos, actualVideos);
    }
}
