package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ImageDatagetImageUrlTest {
    @Test
    public void testGetImageUrl() throws Exception {
        ImageData imageData = new ImageData();
        String expectedUrl = "https://example.com/image.jpg";
        Field imageUrlField = ImageData.class.getDeclaredField("imageUrl");
        imageUrlField.setAccessible(true);
        imageUrlField.set(imageData, expectedUrl);

        String result = imageData.getImageUrl();
        assertEquals(expectedUrl, result);
    }
}
