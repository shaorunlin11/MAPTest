package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class OembedInformationsetUrlTest {
    private OembedInformation oembedInformation;
    private Field urlField;

    @Before
    public void setUp() throws Exception {
        oembedInformation = new OembedInformation();
        urlField = OembedInformation.class.getDeclaredField("url");
        urlField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        urlField.setAccessible(false);
    }

    @Test
    public void testSetUrlSetsUrlField() throws Exception {
        String testUrl = "https://example.com";
        oembedInformation.setUrl(testUrl);
        Assert.assertEquals(testUrl, urlField.get(oembedInformation));
    }

    @Test
    public void testSetUrlWithNullValue() throws Exception {
        oembedInformation.setUrl(null);
        Assert.assertNull(urlField.get(oembedInformation));
    }
}
