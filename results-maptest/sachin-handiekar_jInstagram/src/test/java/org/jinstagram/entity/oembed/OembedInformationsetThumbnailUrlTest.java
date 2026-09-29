package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OembedInformationsetThumbnailUrlTest {
    private OembedInformation oembedInformation;

    @Before
    public void setUp() {
        oembedInformation = new OembedInformation();
    }

    @After
    public void tearDown() {
        oembedInformation = null;
    }

    @Test
    public void testSetThumbnailUrlWithNonNullValue() {
        String expectedThumbnailUrl = "https://example.com/thumbnail.jpg";
        oembedInformation.setThumbnailUrl(expectedThumbnailUrl);
        Assert.assertEquals(expectedThumbnailUrl, oembedInformation.getThumbnailUrl());
    }

    @Test
    public void testSetThumbnailUrlWithNullValue() {
        oembedInformation.setThumbnailUrl(null);
        Assert.assertNull(oembedInformation.getThumbnailUrl());
    }
}
