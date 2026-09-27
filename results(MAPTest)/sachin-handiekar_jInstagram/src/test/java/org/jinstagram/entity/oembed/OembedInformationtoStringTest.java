package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationtoStringTest {

    @Test
    public void testToStringWithAllFieldsNonNull() {
        OembedInformation oembed = new OembedInformation();
        setField(oembed, "providerUrl", "provider_url_value");
        setField(oembed, "title", "title_value");
        setField(oembed, "url", "url_value");
        setField(oembed, "authorName", "author_name_value");
        setField(oembed, "height", "height_value");
        setField(oembed, "width", "width_value");
        setField(oembed, "version", "version_value");
        setField(oembed, "authorUrl", "author_url_value");
        setField(oembed, "providerName", "provider_name_value");
        setField(oembed, "type", "type_value");
        setField(oembed, "mediaId", "mediaId_value");

        String result = oembed.toString();
        String expected = "OembedInformation [provider_url=provider_url_value, title=title_value, url=url_value, author_name=author_name_value, height=height_value, width=width_value, version=version_value, author_url=author_url_value, provider_name=provider_name_value, type=type_value, mediaId=mediaId_value]";
        assertEquals(expected, result);
    }

    @Test
    public void testToStringWithSomeFieldsNull() {
        OembedInformation oembed = new OembedInformation();
        setField(oembed, "providerUrl", "provider_url_value");
        setField(oembed, "title", null);
        setField(oembed, "url", "url_value");
        setField(oembed, "authorName", null);
        setField(oembed, "height", "height_value");
        setField(oembed, "width", null);
        setField(oembed, "version", "version_value");
        setField(oembed, "authorUrl", null);
        setField(oembed, "providerName", "provider_name_value");
        setField(oembed, "type", null);
        setField(oembed, "mediaId", "mediaId_value");

        String result = oembed.toString();
        String expected = "OembedInformation [provider_url=provider_url_value, url=url_value, height=height_value, version=version_value, provider_name=provider_name_value, mediaId=mediaId_value]";
        assertEquals(expected, result);
    }

    @Test
    public void testToStringWithAllFieldsNull() {
        OembedInformation oembed = new OembedInformation();

        String result = oembed.toString();
        String expected = "OembedInformation []";
        assertEquals(expected, result);
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field " + fieldName, e);
        }
    }
}
