package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImageDatatoStringTest {

    @Test
    public void testToStringWithNonNullValues() {
        ImageData imageData = new ImageData();
        imageData.setImageHeight(1080);
        imageData.setImageUrl("https://example.com/image.jpg");
        imageData.setImageWidth(1920);

        String result = imageData.toString();
        assertEquals("ImageData [imageHeight=1080, imageUrl=https://example.com/image.jpg, imageWidth=1920]", result);
    }

    @Test
    public void testToStringWithNullImageUrl() {
        ImageData imageData = new ImageData();
        imageData.setImageHeight(720);
        imageData.setImageUrl(null);
        imageData.setImageWidth(1280);

        String result = imageData.toString();
        assertEquals("ImageData [imageHeight=720, imageUrl=null, imageWidth=1280]", result);
    }
}
