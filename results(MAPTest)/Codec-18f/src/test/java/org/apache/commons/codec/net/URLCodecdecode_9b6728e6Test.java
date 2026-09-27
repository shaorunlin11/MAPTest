package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.apache.commons.codec.DecoderException;

public class URLCodecdecode_9b6728e6Test {
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
    public void testDecodeNullInput() throws DecoderException {
        String result = urlCodec.decode((String) null);
        Assert.assertNull(result);
    }

@Test
    public void testDecodeTargetLine301() throws DecoderException {
        String input = "test%20string";
        String result = urlCodec.decode(input);
        Assert.assertEquals("test string", result);
    }
}
