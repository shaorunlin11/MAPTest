package org.apache.commons.codec.net;

import org.junit.Test;

import java.nio.charset.Charset;


public class QCodecGetCharsetZeroCoverageTest {
    @Test
    public void testGetCharset() {
        // Create an instance of QCodec with a non-null charset
        QCodec qCodec = new QCodec(Charset.forName("UTF-8"));

        // Invoke the method under test
        Charset result = qCodec.getCharset();

        // Assert that the returned charset is not null
        assert result != null;
    }
}
