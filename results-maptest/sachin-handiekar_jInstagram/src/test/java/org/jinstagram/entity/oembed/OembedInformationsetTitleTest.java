package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Assert;

public class OembedInformationsetTitleTest {
    @Test
    public void testSetTitle() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedTitle = "Test Title";

        oembed.setTitle(expectedTitle);

        Assert.assertEquals("Title should be set correctly", expectedTitle, oembed.getTitle());
    }
}
