package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationgetThumbnailWidthTest {

    @Test
    public void testGetThumbnailWidth() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        int expectedThumbnailWidth = 320;

        // Use reflection to set the private field
        Field thumbnailWidthField = OembedInformation.class.getDeclaredField("thumbnailWidth");
        thumbnailWidthField.setAccessible(true);
        thumbnailWidthField.set(oembedInfo, expectedThumbnailWidth);

        int actualThumbnailWidth = oembedInfo.getThumbnailWidth();
        assertEquals(expectedThumbnailWidth, actualThumbnailWidth);
    }
}
