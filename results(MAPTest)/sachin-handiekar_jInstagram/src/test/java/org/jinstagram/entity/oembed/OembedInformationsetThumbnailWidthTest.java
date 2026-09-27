package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationsetThumbnailWidthTest {

    @Test
    public void testSetThumbnailWidth() throws Exception {
        OembedInformation oembed = new OembedInformation();
        int expected = 123;
        oembed.setThumbnailWidth(expected);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = OembedInformation.class.getDeclaredField("thumbnailWidth");
        field.setAccessible(true);
        Integer actual = (Integer) field.get(oembed);

        assertEquals(expected, actual.intValue());
    }
}
