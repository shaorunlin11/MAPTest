package org.jinstagram.entity.users.feed;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MediaFeedDataSetCarouselMediaZeroCoverageTest {
    @Test
    public void testSetCarouselMedia() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        List<CarouselMedia> carouselMedia = new ArrayList<CarouselMedia>();
        // Add some dummy data to the carouselMedia list
        carouselMedia.add(new CarouselMedia());
        mediaFeedData.setCarouselMedia(carouselMedia);
    }
}
