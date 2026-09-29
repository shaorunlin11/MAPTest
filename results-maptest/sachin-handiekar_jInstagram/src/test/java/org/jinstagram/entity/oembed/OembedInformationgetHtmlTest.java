package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetHtmlTest {

    @Test
    public void testGetHtmlReturnsNullWhenNotSet() {
        OembedInformation oembed = new OembedInformation();
        assertNull("getHtml should return null when html is not set", oembed.getHtml());
    }

    @Test
    public void testGetHtmlReturnsSetHtmlValue() {
        OembedInformation oembed = new OembedInformation();
        String expectedHtml = "<div>Test HTML</div>";
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field field = OembedInformation.class.getDeclaredField("html");
            field.setAccessible(true);
            field.set(oembed, expectedHtml);
        } catch (Exception e) {
            fail("Failed to set html field: " + e.getMessage());
        }
        assertEquals("getHtml should return the set html value", expectedHtml, oembed.getHtml());
    }
}
