package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OembedInformationsetWidthTest {
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
    public void testSetWidth() {
        String expectedWidth = "1234";
        oembedInformation.setWidth(expectedWidth);
        Assert.assertEquals("The width should be set correctly", expectedWidth, oembedInformation.getWidth());
    }
}
