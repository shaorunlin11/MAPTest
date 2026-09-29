package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetProviderUrlTest {

    @Test
    public void testGetProviderUrlReturnsNullWhenNotSet() {
        OembedInformation oembedInfo = new OembedInformation();
        assertNull(oembedInfo.getProviderUrl());
    }

    @Test
    public void testGetProviderUrlReturnsSetProviderUrl() {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedProviderUrl = "https://example.com";
        oembedInfo.setProviderUrl(expectedProviderUrl);
        assertEquals(expectedProviderUrl, oembedInfo.getProviderUrl());
    }
}
