package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationgetThumbnailUrlTest {

    @Test
    public void testGetThumbnailUrl() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedThumbnailUrl = "https://example.com/thumbnail.jpg";

        // Set the thumbnailUrl field using reflection
        Field thumbnailUrlField = OembedInformation.class.getDeclaredField("thumbnailUrl");
        thumbnailUrlField.setAccessible(true);
        thumbnailUrlField.set(oembedInfo, expectedThumbnailUrl);

        String actualThumbnailUrl = oembedInfo.getThumbnailUrl();
        assertEquals(expectedThumbnailUrl, actualThumbnailUrl);
    }
}
