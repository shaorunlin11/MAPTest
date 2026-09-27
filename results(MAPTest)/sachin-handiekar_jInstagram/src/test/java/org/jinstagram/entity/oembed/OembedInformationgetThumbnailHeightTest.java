package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetThumbnailHeightTest {

    @Test
    public void testGetThumbnailHeight() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        int expectedThumbnailHeight = 120;

        // Use reflection to set the private field
        java.lang.reflect.Field thumbnailHeightField = OembedInformation.class.getDeclaredField("thumbnailHeight");
        thumbnailHeightField.setAccessible(true);
        thumbnailHeightField.set(oembedInfo, expectedThumbnailHeight);

        int actualThumbnailHeight = oembedInfo.getThumbnailHeight();
        assertEquals(expectedThumbnailHeight, actualThumbnailHeight);
    }
}
