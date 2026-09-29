package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationsetMediaIdTest {

    @Test
    public void testSetMediaIdWithNonNullValue() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedMediaId = "12345";
        oembedInfo.setMediaId(expectedMediaId);

        // Use reflection to verify the field value
        java.lang.reflect.Field mediaIdField = OembedInformation.class.getDeclaredField("mediaId");
        mediaIdField.setAccessible(true);
        String actualMediaId = (String) mediaIdField.get(oembedInfo);

        assertEquals(expectedMediaId, actualMediaId);
    }

    @Test
    public void testSetMediaIdWithNullValue() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        oembedInfo.setMediaId(null);

        // Use reflection to verify the field value
        java.lang.reflect.Field mediaIdField = OembedInformation.class.getDeclaredField("mediaId");
        mediaIdField.setAccessible(true);
        String actualMediaId = (String) mediaIdField.get(oembedInfo);

        assertNull(actualMediaId);
    }
}
