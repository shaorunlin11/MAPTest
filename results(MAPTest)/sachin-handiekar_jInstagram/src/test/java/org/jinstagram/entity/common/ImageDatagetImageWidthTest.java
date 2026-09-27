package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class ImageDatagetImageWidthTest {
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
    public void testGetImageWidth() throws Exception {
        int expectedWidth = 800;
        Field imageWidthField = ImageData.class.getDeclaredField("imageWidth");
        imageWidthField.setAccessible(true);
        imageWidthField.set(imageData, expectedWidth);
        int actualWidth = imageData.getImageWidth();
        Assert.assertEquals("getImageWidth should return the correct image width", expectedWidth, actualWidth);
    }
}
