package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class ImageDatagetImageHeightTest {
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
    public void testGetImageHeight() throws Exception {
        int expectedHeight = 1080;
        Field imageHeightField = ImageData.class.getDeclaredField("imageHeight");
        imageHeightField.setAccessible(true);
        imageHeightField.set(imageData, expectedHeight);
        int actualHeight = imageData.getImageHeight();
        Assert.assertEquals("getImageHeight should return the correct image height", expectedHeight, actualHeight);
    }
}
