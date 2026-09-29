package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class ImageDatasetImageWidthTest {
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
    public void testSetImageWidth() {
        int expectedWidth = 800;
        imageData.setImageWidth(expectedWidth);
        Assert.assertEquals("Image width should be set correctly", expectedWidth, imageData.getImageWidth());
    }
}
