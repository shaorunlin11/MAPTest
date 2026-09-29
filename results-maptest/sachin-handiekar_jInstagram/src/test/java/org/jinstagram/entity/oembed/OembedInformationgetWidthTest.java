package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetWidthTest {

    @Test
    public void testGetWidth() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedWidth = "1234";

        // Use reflection to set the private field
        java.lang.reflect.Field widthField = oembedInfo.getClass().getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.set(oembedInfo, expectedWidth);

        String actualWidth = oembedInfo.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }
}
