package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class URLCodecgetEncodingTest {
    private URLCodec urlCodec;

    @Before
    public void setUp() {
        urlCodec = new URLCodec();
    }

    @After
    public void tearDown() {
        urlCodec = null;
    }

    @Test
    public void testGetEncodingReturnsDefaultCharset() {
        Assert.assertEquals("UTF-8", urlCodec.getEncoding());
    }

    @Test
    public void testGetEncodingReturnsSetCharset() {
        URLCodec codec = new URLCodec("ISO-8859-1");
        Assert.assertEquals("ISO-8859-1", codec.getEncoding());
    }

    @Test
    public void testGetEncodingReturnsNullIfNotSet() {
        URLCodec codec = new URLCodec((String) null);
        Assert.assertNull(codec.getEncoding());
    }
}
