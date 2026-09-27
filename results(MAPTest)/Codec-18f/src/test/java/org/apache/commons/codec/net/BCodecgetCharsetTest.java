package org.apache.commons.codec.net;

import org.junit.Test;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;

public class BCodecgetCharsetTest {

    @Test
    public void testGetCharsetWithDefaultConstructor() {
        BCodec codec = new BCodec();
        assertEquals(Charset.forName("UTF-8"), codec.getCharset());
    }

    @Test
    public void testGetCharsetWithCharsetConstructor() {
        Charset customCharset = Charset.forName("UTF-16");
        BCodec codec = new BCodec(customCharset);
        assertEquals(customCharset, codec.getCharset());
    }

    @Test
    public void testGetCharsetWithStringConstructor() {
        BCodec codec = new BCodec("UTF-16");
        assertEquals(Charset.forName("UTF-16"), codec.getCharset());
    }
}
