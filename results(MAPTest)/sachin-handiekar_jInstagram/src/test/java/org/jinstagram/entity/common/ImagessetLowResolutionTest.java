package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.ImageData;

public class ImagessetLowResolutionTest {
    private Images images;
    private ImageData mockLowResolution;

    @Before
    public void setUp() {
        images = new Images();
        mockLowResolution = new ImageData();
    }

    @Test
    public void testSetLowResolutionSetsLowResolutionField() throws Exception {
        images.setLowResolution(mockLowResolution);

        java.lang.reflect.Field field = Images.class.getDeclaredField("lowResolution");
        field.setAccessible(true);
        ImageData result = (ImageData) field.get(images);

        Assert.assertEquals(mockLowResolution, result);
    }

    @Test
    public void testSetLowResolutionDoesNotModifyOtherFields() throws Exception {
        images.setLowResolution(mockLowResolution);

        java.lang.reflect.Field standardResolutionField = Images.class.getDeclaredField("standardResolution");
        standardResolutionField.setAccessible(true);
        ImageData standardResolution = (ImageData) standardResolutionField.get(images);
        Assert.assertNull(standardResolution);

        java.lang.reflect.Field thumbnailField = Images.class.getDeclaredField("thumbnail");
        thumbnailField.setAccessible(true);
        ImageData thumbnail = (ImageData) thumbnailField.get(images);
        Assert.assertNull(thumbnail);
    }
}
