package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class ImageDatasetImageHeightTest {
    private ImageData imageData;

    @Before
    public void setUp() {
        imageData = new ImageData();
    }

    @After
    public void tearDown() {
        imageData = null;
    }

    @Test
    public void testSetImageHeight() throws Exception {
        int expectedHeight = 1080;
        imageData.setImageHeight(expectedHeight);
        Assert.assertEquals("Image height should be set correctly", expectedHeight, imageData.getImageHeight());
    }
}
