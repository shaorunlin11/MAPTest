package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Images;

public class MediaFeedDatagetImagesTest {

    @Test
    public void testGetImages() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        Images expectedImages = new Images();

        // Use reflection to set private field
        java.lang.reflect.Field imagesField = MediaFeedData.class.getDeclaredField("images");
        imagesField.setAccessible(true);
        imagesField.set(mediaFeedData, expectedImages);

        Images actualImages = mediaFeedData.getImages();

        Assert.assertEquals(expectedImages, actualImages);
    }
}
