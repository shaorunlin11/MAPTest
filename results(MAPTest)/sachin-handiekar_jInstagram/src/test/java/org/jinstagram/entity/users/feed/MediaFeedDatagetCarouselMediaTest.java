package org.jinstagram.entity.users.feed;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatagetCarouselMediaTest {

    @Test
    public void testGetCarouselMedia_returnsCorrectValue() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        List<CarouselMedia> expectedCarouselMedia = new ArrayList<CarouselMedia>();
        expectedCarouselMedia.add(new CarouselMedia());

        // Use reflection to set the private field
        java.lang.reflect.Field carouselMediaField = MediaFeedData.class.getDeclaredField("carouselMedia");
        carouselMediaField.setAccessible(true);
        carouselMediaField.set(mediaFeedData, expectedCarouselMedia);

        List<CarouselMedia> result = mediaFeedData.getCarouselMedia();
        assertEquals(expectedCarouselMedia, result);
    }

    @Test
    public void testGetCarouselMedia_returnsNullWhenNotSet() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();

        // Use reflection to set the private field to null
        java.lang.reflect.Field carouselMediaField = MediaFeedData.class.getDeclaredField("carouselMedia");
        carouselMediaField.setAccessible(true);
        carouselMediaField.set(mediaFeedData, null);

        List<CarouselMedia> result = mediaFeedData.getCarouselMedia();
        assertNull(result);
    }

    @Test
    public void testGetCarouselMedia_returnsEmptyListWhenEmpty() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();

        // Use reflection to set the private field to an empty list
        java.lang.reflect.Field carouselMediaField = MediaFeedData.class.getDeclaredField("carouselMedia");
        carouselMediaField.setAccessible(true);
        carouselMediaField.set(mediaFeedData, new ArrayList<CarouselMedia>());

        List<CarouselMedia> result = mediaFeedData.getCarouselMedia();
        assertTrue(result.isEmpty());
    }
}
