package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetUrlTest {

    @Test
    public void testGetUrl_returnsNullWhenNotSet() {
        OembedInformation oembedInformation = new OembedInformation();
        assertNull(oembedInformation.getUrl());
    }

    @Test
    public void testGetUrl_returnsSetUrl() {
        OembedInformation oembedInformation = new OembedInformation();
        String expectedUrl = "https://example.com";
        oembedInformation.setUrl(expectedUrl);
        assertEquals(expectedUrl, oembedInformation.getUrl());
    }
}
