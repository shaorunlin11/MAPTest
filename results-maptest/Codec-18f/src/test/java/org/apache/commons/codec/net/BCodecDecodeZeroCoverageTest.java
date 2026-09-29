package org.apache.commons.codec.net;

import org.junit.Test;

import org.apache.commons.codec.DecoderException;


public class BCodecDecodeZeroCoverageTest {
    @Test
    public void testDecodeWithNullValue() throws DecoderException {
        BCodec bCodec = new BCodec();
        String result = bCodec.decode(null);
        // Target line 177 is executed when value is null, which returns null
        // No further assertions needed as per requirements
    }

@Test
    public void testDecodeWithNonNullValue() throws DecoderException {
        BCodec bCodec = new BCodec();
        String result = bCodec.decode("=?UTF-8?B?dGVzdA==?=");
        // Target line 181 is executed when value is not null, which calls this.decodeText(value)
        // No further assertions needed as per requirements
    }
}
