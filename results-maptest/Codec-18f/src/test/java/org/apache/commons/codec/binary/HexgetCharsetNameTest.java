package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.nio.charset.Charset;

public class HexgetCharsetNameTest {
    @Test
    public void testGetCharsetName_Default() {
        Hex hex = new Hex();
        assertEquals("UTF-8", hex.getCharsetName());
    }

    @Test
    public void testGetCharsetName_CustomCharset() {
        Charset customCharset = Charset.forName("ISO-8859-1");
        Hex hex = new Hex(customCharset);
        assertEquals("ISO-8859-1", hex.getCharsetName());
    }

    @Test
    public void testGetCharsetName_CustomName() {
        Hex hex = new Hex("UTF-8");
        assertEquals("UTF-8", hex.getCharsetName());
    }
}
