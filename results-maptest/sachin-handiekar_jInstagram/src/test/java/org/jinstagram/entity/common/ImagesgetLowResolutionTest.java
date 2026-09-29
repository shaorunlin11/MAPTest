package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class ImagesgetLowResolutionTest {
    @Test
    public void testGetLowResolution() throws Exception {
        Images images = new Images();
        ImageData expected = new ImageData();

        // Use reflection to set the private field
        java.lang.reflect.Field field = Images.class.getDeclaredField("lowResolution");
        field.setAccessible(true);
        field.set(images, expected);

        ImageData result = images.getLowResolution();
        Assert.assertEquals(expected, result);
    }
}
