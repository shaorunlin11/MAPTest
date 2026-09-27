package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationsetHeightTest {

    @Test
    public void testSetHeight() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String testHeight = "480";

        oembed.setHeight(testHeight);

        Field heightField = OembedInformation.class.getDeclaredField("height");
        heightField.setAccessible(true);
        String actualHeight = (String) heightField.get(oembed);

        assertEquals("The height should be set correctly", testHeight, actualHeight);
    }
}
