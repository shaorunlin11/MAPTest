package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class ImagesgetThumbnailTest {
    private Images images;

    @Before
    public void setUp() {
        images = new Images();
    }

    @After
    public void tearDown() {
        images = null;
    }

    @Test
    public void testGetThumbnailReturnsNullWhenNotSet() {
        Assert.assertNull(images.getThumbnail());
    }

    @Test
    public void testGetThumbnailReturnsSetThumbnail() throws Exception {
        ImageData expectedThumbnail = new ImageData();
        Field thumbnailField = Images.class.getDeclaredField("thumbnail");
        thumbnailField.setAccessible(true);
        thumbnailField.set(images, expectedThumbnail);

        ImageData result = images.getThumbnail();
        Assert.assertEquals(expectedThumbnail, result);
    }
}
