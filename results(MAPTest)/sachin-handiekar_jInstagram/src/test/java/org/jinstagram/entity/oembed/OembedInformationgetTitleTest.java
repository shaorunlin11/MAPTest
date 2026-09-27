package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationgetTitleTest {

    @Test
    public void testGetTitle() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedTitle = "Test Title";

        // Use reflection to set private field
        Field titleField = OembedInformation.class.getDeclaredField("title");
        titleField.setAccessible(true);
        titleField.set(oembed, expectedTitle);

        String actualTitle = oembed.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetTitleWithNull() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String actualTitle = oembed.getTitle();
        assertNull(actualTitle);
    }
}
