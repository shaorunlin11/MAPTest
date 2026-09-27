package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class OembedInformationgetAuthorUrlTest {

    @Test
    public void testGetAuthorUrl() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedAuthorUrl = "https://example.com/author";

        // Use reflection to set the private field
        Field authorUrlField = OembedInformation.class.getDeclaredField("authorUrl");
        authorUrlField.setAccessible(true);
        authorUrlField.set(oembedInfo, expectedAuthorUrl);

        String actualAuthorUrl = oembedInfo.getAuthorUrl();
        Assert.assertEquals(expectedAuthorUrl, actualAuthorUrl);
    }
}
