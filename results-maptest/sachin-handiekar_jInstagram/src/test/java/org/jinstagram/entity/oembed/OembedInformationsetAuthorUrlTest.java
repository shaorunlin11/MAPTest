package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class OembedInformationsetAuthorUrlTest {

    @Test
    public void testSetAuthorUrl() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedAuthorUrl = "https://example.com/author";

        oembed.setAuthorUrl(expectedAuthorUrl);

        Field authorUrlField = OembedInformation.class.getDeclaredField("authorUrl");
        authorUrlField.setAccessible(true);
        String actualAuthorUrl = (String) authorUrlField.get(oembed);

        Assert.assertEquals(expectedAuthorUrl, actualAuthorUrl);
    }

    @Test
    public void testSetAuthorUrlWithNull() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedAuthorUrl = null;

        oembed.setAuthorUrl(expectedAuthorUrl);

        Field authorUrlField = OembedInformation.class.getDeclaredField("authorUrl");
        authorUrlField.setAccessible(true);
        String actualAuthorUrl = (String) authorUrlField.get(oembed);

        Assert.assertNull(actualAuthorUrl);
    }
}
