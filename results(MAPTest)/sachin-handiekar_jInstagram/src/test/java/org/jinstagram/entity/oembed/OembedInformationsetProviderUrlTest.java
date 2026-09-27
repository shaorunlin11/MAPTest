package org.jinstagram.entity.oembed;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class OembedInformationsetProviderUrlTest {

    @Test
    public void testSetProviderUrl() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedProviderUrl = "https://example.com/provider";

        oembedInfo.setProviderUrl(expectedProviderUrl);

        Field providerUrlField = OembedInformation.class.getDeclaredField("providerUrl");
        providerUrlField.setAccessible(true);
        String actualProviderUrl = (String) providerUrlField.get(oembedInfo);

        assertEquals(expectedProviderUrl, actualProviderUrl);
    }

    @Test
    public void testSetProviderUrlWithNull() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedProviderUrl = null;

        oembedInfo.setProviderUrl(expectedProviderUrl);

        Field providerUrlField = OembedInformation.class.getDeclaredField("providerUrl");
        providerUrlField.setAccessible(true);
        String actualProviderUrl = (String) providerUrlField.get(oembedInfo);

        assertEquals(expectedProviderUrl, actualProviderUrl);
    }
}
