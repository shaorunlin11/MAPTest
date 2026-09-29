package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

public class HextoStringTest {
    @Test
    public void testToString() throws Exception {
        Hex hex = new Hex();
        String result = hex.toString();
        assertTrue(result.contains("[charsetName=" + hex.getCharset() + "]"));
    }

    @Test
    public void testToStringWithCustomCharset() throws Exception {
        Charset customCharset = Charset.forName("UTF-16");
        Hex hex = new Hex(customCharset);
        String result = hex.toString();
        assertTrue(result.contains("[charsetName=" + customCharset + "]"));
    }
}
