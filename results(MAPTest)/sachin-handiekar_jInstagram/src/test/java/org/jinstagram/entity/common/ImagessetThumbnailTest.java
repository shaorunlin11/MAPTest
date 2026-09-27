package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.ImageData;

public class ImagessetThumbnailTest {
    private Images images;
    private ImageData imageData;

    @Before
    public void setUp() {
        images = new Images();
        imageData = new ImageData();
    }

    @After
    public void tearDown() {
        images = null;
        imageData = null;
    }

    @Test
    public void testSetThumbnailSetsThumbnailCorrectly() throws Exception {
        images.setThumbnail(imageData);
        Assert.assertEquals(imageData, images.getThumbnail());
    }
}
