package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Images;

public class MediaFeedDatasetImagesTest {
    private MediaFeedData mediaFeedData;
    private Images images;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        images = new Images();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
        images = null;
    }

    @Test
    public void testSetImages() {
        // Act
        mediaFeedData.setImages(images);

        // Assert
        Assert.assertEquals(images, mediaFeedData.getImages());
    }
}
