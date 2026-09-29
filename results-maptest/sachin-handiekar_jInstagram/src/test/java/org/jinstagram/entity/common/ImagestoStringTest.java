package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImagestoStringTest {

    @Test
    public void testToString() throws Exception {
        Images images = new Images();

        ImageData lowResolution = new ImageData();
        ImageData standardResolution = new ImageData();
        ImageData thumbnail = new ImageData();

        // Set the fields using reflection to bypass private access
        java.lang.reflect.Field lowResolutionField = Images.class.getDeclaredField("lowResolution");
        lowResolutionField.setAccessible(true);
        lowResolutionField.set(images, lowResolution);

        java.lang.reflect.Field standardResolutionField = Images.class.getDeclaredField("standardResolution");
        standardResolutionField.setAccessible(true);
        standardResolutionField.set(images, standardResolution);

        java.lang.reflect.Field thumbnailField = Images.class.getDeclaredField("thumbnail");
        thumbnailField.setAccessible(true);
        thumbnailField.set(images, thumbnail);

        String result = images.toString();

        assertTrue(result.contains("lowResolution="));
        assertTrue(result.contains("standardResolution="));
        assertTrue(result.contains("thumbnail="));
    }
}
