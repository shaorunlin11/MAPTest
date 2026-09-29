package org.apache.commons.codec.binary;

import org.junit.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class HexgetCharsetTest {
    @Test
    public void testGetCharset_DefaultConstructor() {
        Hex hex = new Hex();
        assertEquals(StandardCharsets.UTF_8, hex.getCharset());
    }

    @Test
    public void testGetCharset_CharsetConstructor() {
        Charset customCharset = Charset.forName("UTF-16");
        Hex hex = new Hex(customCharset);
        assertEquals(customCharset, hex.getCharset());
    }

    @Test
    public void testGetCharset_StringConstructor() {
        Charset customCharset = Charset.forName("UTF-16");
        Hex hex = new Hex(customCharset.name());
        assertEquals(customCharset, hex.getCharset());
    }
}
