package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OembedInformationsetThumbnailHeightTest {

    @Test
    public void testSetThumbnailHeight() throws Exception {
        OembedInformation oembed = new OembedInformation();
        int expected = 100;
        oembed.setThumbnailHeight(expected);

        Field field = OembedInformation.class.getDeclaredField("thumbnailHeight");
        field.setAccessible(true);
        int actual = (Integer) field.get(oembed);

        assertEquals(expected, actual);
    }
}
