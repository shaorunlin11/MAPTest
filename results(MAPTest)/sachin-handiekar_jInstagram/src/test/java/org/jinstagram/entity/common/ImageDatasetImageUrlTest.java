package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ImageDatasetImageUrlTest {
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
    public void testSetImageUrl() throws Exception {
        String expectedUrl = "https://example.com/image.jpg";
        imageData.setImageUrl(expectedUrl);

        Field imageUrlField = ImageData.class.getDeclaredField("imageUrl");
        imageUrlField.setAccessible(true);
        String actualUrl = (String) imageUrlField.get(imageData);

        assertEquals("The image URL should be set correctly", expectedUrl, actualUrl);
    }

    @Test
    public void testSetImageUrlWithNull() throws Exception {
        imageData.setImageUrl(null);

        Field imageUrlField = ImageData.class.getDeclaredField("imageUrl");
        imageUrlField.setAccessible(true);
        String actualUrl = (String) imageUrlField.get(imageData);

        assertNull("The image URL should be null when set to null", actualUrl);
    }
}
