package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OembedInformationsetHtmlTest {
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
    public void testSetHtmlWithNonNullValue() {
        String expectedHtml = "<html>test</html>";
        oembedInformation.setHtml(expectedHtml);
        Assert.assertEquals(expectedHtml, oembedInformation.getHtml());
    }

    @Test
    public void testSetHtmlWithNullValue() {
        oembedInformation.setHtml(null);
        Assert.assertNull(oembedInformation.getHtml());
    }
}
