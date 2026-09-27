package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetMediaIdTest {

    @Test
    public void testGetMediaId() throws Exception {
        OembedInformation oembedInformation = new OembedInformation();
        String expectedMediaId = "testMediaId";

        // Use reflection to set private field
        java.lang.reflect.Field mediaIdField = OembedInformation.class.getDeclaredField("mediaId");
        mediaIdField.setAccessible(true);
        mediaIdField.set(oembedInformation, expectedMediaId);

        String actualMediaId = oembedInformation.getMediaId();
        assertEquals(expectedMediaId, actualMediaId);
    }

    @Test
    public void testGetMediaIdWithNull() throws Exception {
        OembedInformation oembedInformation = new OembedInformation();
        String actualMediaId = oembedInformation.getMediaId();
        assertNull(actualMediaId);
    }
}
